package com.example.h175534.myapplication.album.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h175534.myapplication.R;
import com.example.h175534.myapplication.album.model.PhoneAlbum;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumFolderViewHolder>{

    private static final String TAG = AlbumAdapter.class.getSimpleName();
    LayoutInflater mInflater;
    private Context mContext;
    private List<PhoneAlbum> mData;
    private AlbumClickListener mClickListener;

    public AlbumAdapter(Context context, List<PhoneAlbum> data){
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public AlbumFolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View view = mInflater.inflate(R.layout.album_folder_layout, parent, false);
        return new AlbumFolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumFolderViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder pos : "+position);
        PhoneAlbum phoneAlbum = mData.get(position);
        Log.d(TAG,"phoneAlbum: "+phoneAlbum);
        holder.albumNameView.setText(phoneAlbum.getName());
        Picasso.with(mContext).load("file://media"+phoneAlbum.getCoverUri()).into(holder.albumImageView);
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            Log.d(TAG,"count 0");
            return 0;
        }
        Log.d(TAG,"count :"+mData.size());
        return mData.size();
    }

    public void setData(List<PhoneAlbum> data) {
        Log.d(TAG,"setData data : "+data);
        mData = data;
        notifyDataSetChanged();
    }

    public class AlbumFolderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView albumImageView;
        public TextView albumNameView;

        public AlbumFolderViewHolder(View itemView) {
            super(itemView);
            albumImageView = itemView.findViewById(R.id.iv_image);
            albumNameView = itemView.findViewById(R.id.tv_folder);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onAlbumClicked(v,getAdapterPosition());
            }
        }
    }

    public PhoneAlbum getAlbum(int position){
        return mData.get(position);
    }

    public void setOnAlbumClickListener(AlbumClickListener listener){
        mClickListener = listener;
    }

    public interface AlbumClickListener{
        void onAlbumClicked(View view, int position);
    }
}
