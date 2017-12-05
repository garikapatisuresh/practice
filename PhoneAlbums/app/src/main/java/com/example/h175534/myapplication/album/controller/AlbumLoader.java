package com.example.h175534.myapplication.album.controller;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.h175534.myapplication.album.model.PhoneAlbum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class AlbumLoader extends AsyncTaskLoader<List<PhoneAlbum>> {
    public static final String TAG = AlbumLoader.class.getSimpleName();
    private List<PhoneAlbum> mAlbumList;

    public AlbumLoader(Context context){
        super(context);
    }

    @Override
    public List<PhoneAlbum> loadInBackground() {
        Log.d(TAG, "loadInBackground()");
        if(mAlbumList == null) {
            mAlbumList = new ArrayList<>();
        }

        try {
            mAlbumList = DeviceImageManager.getPhoneAlbums(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG," Albums count : "+mAlbumList.size());
        deliverResult(mAlbumList);

        return mAlbumList;
    }


}
