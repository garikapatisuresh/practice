package com.example.h175534.myapplication.album.controller;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.h175534.myapplication.album.model.PhonePhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class AlbumPhotosLoader extends AsyncTaskLoader<List<PhonePhoto>> {
    public static final String TAG = AlbumPhotosLoader.class.getSimpleName();
    private List<PhonePhoto> mAlbumPhotosList;

    public AlbumPhotosLoader(Context context){
        super(context);
    }

    @Override
    public List<PhonePhoto> loadInBackground() {
        Log.d(TAG, "loadInBackground()");
        if(mAlbumPhotosList == null) {
            mAlbumPhotosList = new ArrayList<>();
        }

        try {
            mAlbumPhotosList = DeviceImageManager.getAllPhotos(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG," Albums count : "+ mAlbumPhotosList.size());
        deliverResult(mAlbumPhotosList);

        return mAlbumPhotosList;
    }


}
