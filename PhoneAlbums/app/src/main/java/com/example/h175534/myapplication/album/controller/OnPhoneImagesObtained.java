package com.example.h175534.myapplication.album.controller;

import com.example.h175534.myapplication.album.model.PhoneAlbum;

import java.util.List;

public interface OnPhoneImagesObtained {

    void onComplete( List<PhoneAlbum> albums );
    void onError();

}