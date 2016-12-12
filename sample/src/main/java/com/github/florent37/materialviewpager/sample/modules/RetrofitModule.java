package com.github.florent37.materialviewpager.sample.modules;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raian on 12/12/16.
 */

@Module
public class RetrofitModule {
    private String baseUrl;
    private static final String TAG = RetrofitModule.class.getSimpleName();

    public RetrofitModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit getRetrofit(){
        Log.d(TAG, "getRetrofit");
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

}
