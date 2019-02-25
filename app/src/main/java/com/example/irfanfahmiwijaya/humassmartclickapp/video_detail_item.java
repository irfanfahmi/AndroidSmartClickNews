package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class video_detail_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail_item);


        final String nama_file = getIntent().getExtras().getString("nama_file") ;
        final VideoView dt_video = findViewById(R.id.detail_video);

        final String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/galeri_video/" + nama_file;
        try {
            Uri videoUri = Uri.parse(fullUrl);
           dt_video.setVideoURI(videoUri);
            dt_video.setTag(videoUri);

            dt_video.setMediaController(new MediaController(this));
            dt_video.start();

        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
    }
}
