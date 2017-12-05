package com.example.h175534.myapplication.album.controller;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.h175534.myapplication.album.model.PhoneAlbum;
import com.example.h175534.myapplication.album.model.PhonePhoto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H175534 on 12/4/2017.
 */

public class DeviceImageManager {

    public static final String TAG = DeviceImageManager.class.getSimpleName();

    public static List<PhoneAlbum> getPhoneAlbums(Context context) throws Exception{

        Log.d(TAG, "getPhoneAlbums()");
        // Creating vectors to hold the final albums objects and albums names
        List<PhoneAlbum> phoneAlbums = new ArrayList<>();
        List< String > albumsNames = new ArrayList<>();

        // which image properties are we querying
        String[] projection = new String[] {
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID
        };

        // content: style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Make the query.
        Cursor cur = context.getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        if ( cur != null && cur.getCount() > 0 ) {
            Log.i("DeviceImageManager"," query count=" + cur.getCount());

            if (cur.moveToFirst()) {
                String bucketName;
                String data;
                String imageId;
                int bucketNameColumn = cur.getColumnIndex(
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

                int imageUriColumn = cur.getColumnIndex(
                        MediaStore.Images.Media.DATA);

                int imageIdColumn = cur.getColumnIndex(
                        MediaStore.Images.Media._ID );

                do {
                    // Get the field values
                    bucketName = cur.getString( bucketNameColumn );
                    data = cur.getString( imageUriColumn );
                    imageId = cur.getString( imageIdColumn );

                    // Adding a new PhonePhoto object to phonePhotos vector
                    PhonePhoto phonePhoto = new PhonePhoto();
                    phonePhoto.setAlbumName( bucketName );
                    phonePhoto.setPhotoUri( data );
                    phonePhoto.setId( Integer.valueOf( imageId ) );

                    if ( albumsNames.contains( bucketName ) ) {
                        for ( PhoneAlbum album : phoneAlbums ) {
                            if ( album.getName().equals( bucketName ) ) {
                                album.getAlbumPhotos().add( phonePhoto );
                                Log.i( "DeviceImageManager", "A photo was added to album => " + bucketName );
                                break;
                            }
                        }
                    } else {
                        PhoneAlbum album = new PhoneAlbum();
                        Log.i( "DeviceImageManager", "A new album was created => " + bucketName );
                        album.setId( phonePhoto.getId() );
                        album.setName( bucketName );
                        album.setCoverUri( phonePhoto.getPhotoUri() );
                        album.getAlbumPhotos().add( phonePhoto );
                        Log.i( "DeviceImageManager", "A photo was added to album => " + bucketName );

                        phoneAlbums.add( album );
                        albumsNames.add( bucketName );
                    }

                } while (cur.moveToNext());
            }

            cur.close();
            return phoneAlbums;
        } else {
            throw new Exception("NO DATA");
        }
    }

    public static void getPhoneAlbumsAsync(Context context , OnPhoneImagesObtained listener ) {
        List<PhoneAlbum> albums = null;
        try {
            albums = getPhoneAlbums(context);
            if(listener != null ){
                listener.onComplete(albums);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(listener != null ){
                listener.onError();
            }
        }
    }

    public static List< PhonePhoto > getAllPhotos(Context context) throws Exception{

        Log.d(TAG, "getPhoneAlbums()");
        // Creating vectors to hold the final albums objects and albums names
        List< PhonePhoto > photoList = new ArrayList<>();

        // which image properties are we querying
        String[] projection = new String[] {
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID
        };

        // content: style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        // Make the query.
        Cursor cur = context.getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        if ( cur != null && cur.getCount() > 0 ) {
            Log.i("DeviceImageManager"," query count=" + cur.getCount());

            if (cur.moveToFirst()) {
                String bucketName;
                String data;
                String imageId;
                int bucketNameColumn = cur.getColumnIndex(
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

                int imageUriColumn = cur.getColumnIndex(
                        MediaStore.Images.Media.DATA);

                int imageIdColumn = cur.getColumnIndex(
                        MediaStore.Images.Media._ID );

                do {
                    // Get the field values
                    bucketName = cur.getString( bucketNameColumn );
                    data = cur.getString( imageUriColumn );
                    imageId = cur.getString( imageIdColumn );

                    // Adding a new PhonePhoto object to phonePhotos vector
                    PhonePhoto phonePhoto = new PhonePhoto();
                    phonePhoto.setAlbumName( bucketName );
                    phonePhoto.setPhotoUri( data );
                    phonePhoto.setId( Integer.valueOf( imageId ) );

                   photoList.add(phonePhoto);

                } while (cur.moveToNext());
            }

            cur.close();
            return photoList;
        } else {
            throw new Exception("NO DATA");
        }
    }
}
