package com.example.h175534.myapplication.album.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PhoneAlbum {

    private int id;
    private String name;
    private String coverUri;
    private List<PhonePhoto> albumPhotos;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri( String albumCoverUri ) {
        this.coverUri = albumCoverUri;
    }

    public List< PhonePhoto > getAlbumPhotos() {
        if ( albumPhotos == null ) {
            albumPhotos = new ArrayList<>();
        }
        return albumPhotos;
    }

    public void setAlbumPhotos( List< PhonePhoto > albumPhotos ) {
        this.albumPhotos = albumPhotos;
    }

    @Override
    public String toString() {
        return "Name : "+name + " Cover Uri : "+coverUri;
    }
}