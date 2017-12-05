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
import com.example.h175534.myapplication.album.controller.AlbumLoader;
import com.example.h175534.myapplication.album.model.PhoneAlbum;

import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class AlbumFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<PhoneAlbum>>{

    private static final String TAG = AlbumFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private AlbumAdapter mAlbumAdapter;

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
        mAlbumAdapter = new AlbumAdapter(getActivity(), null);
        mRecyclerView.setAdapter(mAlbumAdapter);
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
    public Loader<List<PhoneAlbum>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG,"onCreateLoader()");
        return new AlbumLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<PhoneAlbum>> loader, List<PhoneAlbum> data) {
        Log.d(TAG,"onLoadFinished()");
        mAlbumAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<PhoneAlbum>> loader) {
        Log.d(TAG,"onLoaderReset()");
        mAlbumAdapter.setData(null);
    }
}
