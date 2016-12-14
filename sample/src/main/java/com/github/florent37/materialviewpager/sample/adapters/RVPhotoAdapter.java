package com.github.florent37.materialviewpager.sample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.model.Photo;

import java.util.List;

/**
 * Created by raian on 12/12/16.
 */

public class RVPhotoAdapter extends RecyclerView.Adapter<RVPhotoAdapter.ViewHolder>{
    private Context context;
    private List<Photo> lstRes;

    public RVPhotoAdapter(Context context, List<Photo> lstRes) {
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewTitle.setText(lstRes.get(position).getOwner());
        Glide.with(context)
                .load("http://www.freemagebank.com/wp-content/uploads/edd/2014/12/XB000011-1560x1560.jpg")
                .centerCrop()
                .fitCenter()
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageViewThumbnail;
        TextView mTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewThumbnail = (ImageView) itemView.findViewById(R.id.mImageViewThumbnail);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.mTextViewTitle);
        }
    }
}
