package com.example.h175534.myapplication.album.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h175534.myapplication.R;
import com.example.h175534.myapplication.album.controller.AlbumPhotosLoader;
import com.example.h175534.myapplication.album.model.PhonePhoto;

import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class AlbumPhotosFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<PhonePhoto>>{

    private static final String TAG = AlbumPhotosFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private AlbumPhotosAdapter mAlbumPhotosAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.album_fragment_layout,container,false);
        mRecyclerView = view.findViewById(R.id.albums_grid_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mAlbumPhotosAdapter = new AlbumPhotosAdapter(getActivity(), null);
        mRecyclerView.setAdapter(mAlbumPhotosAdapter);
        Log.d(TAG,"initLoader()");
        getLoaderManager().initLoader(0,null,this).forceLoad();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public Loader<List<PhonePhoto>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG,"onCreateLoader()");
        return new AlbumPhotosLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<PhonePhoto>> loader, List<PhonePhoto> data) {
        Log.d(TAG,"onLoadFinished()");
        mAlbumPhotosAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<PhonePhoto>> loader) {
        Log.d(TAG,"onLoaderReset()");
        mAlbumPhotosAdapter.setData(null);
    }
}
