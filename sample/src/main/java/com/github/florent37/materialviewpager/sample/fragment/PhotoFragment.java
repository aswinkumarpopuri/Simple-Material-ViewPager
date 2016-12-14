package com.github.florent37.materialviewpager.sample.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.adapters.RVPhotoAdapter;
import com.github.florent37.materialviewpager.sample.components.DaggerRetrofitComponent;
import com.github.florent37.materialviewpager.sample.model.FlickrResult;
import com.github.florent37.materialviewpager.sample.modules.RetrofitModule;
import com.github.florent37.materialviewpager.sample.rest.RetrofitService;


import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment {
    private static final String TAG = PhotoFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    Retrofit retrofit;
    private String baseURL = "https://api.flickr.com/";
    private static final String API_KEY = "52b082213eb8821fb5a02307573b4c7d";
    private static final String method = "flickr.photos.getRecent";
    private static final String perPage = "100";
    private static final String format = "json";
    private static final String callBack = "nojsoncallback";

    private RecyclerView mPhotoRV;
    private RVPhotoAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public PhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance(String param1, String param2) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        mPhotoRV = (RecyclerView) view.findViewById(R.id.mPhotoRV);
        layoutManager = new LinearLayoutManager(getContext());

        mPhotoRV.setLayoutManager(layoutManager);
        mPhotoRV.setHasFixedSize(true);



        DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule(baseURL))
                .build()
                .inject(this);

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Observable<FlickrResult> flickrResultObservable = retrofitService.getPhoto(method, API_KEY, perPage, format, callBack);
        flickrResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FlickrResult>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError::" + e.getMessage());

                    }

                    @Override
                    public void onNext(FlickrResult flickrResult) {
                        Log.d(TAG, "onNext");
                        adapter = new RVPhotoAdapter(getContext(), flickrResult.getPhotos().getPhoto());
                        mPhotoRV.setAdapter(adapter);

                    }
                });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
