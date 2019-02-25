package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class pemerintah_detail_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemerintah_detail_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String nama_kegiatan  = getIntent().getExtras().getString("nama_kegiatanp");
        String tanggal_kegiatan = getIntent().getExtras().getString("tanggal_kegiatanp");
        String tempat_kegiatan = getIntent().getExtras().getString("tempat_kegiatanp") ;
        String deskripsi_kegiatan = getIntent().getExtras().getString("deskripsi_kegiatanp");
        String foto_kegiatan = getIntent().getExtras().getString("foto_kegiatanp") ;


        // ini views
        TextView tv_nama_kg = findViewById(R.id.nama_dkegiatanp);
        TextView tv_tgl_kg = findViewById(R.id.tanggal_dkegiatanp);
        TextView tv_tmpt_kg = findViewById(R.id.tempat_dkegiatanp) ;
        TextView tv_dsc_kg = findViewById(R.id.deskripsi_dkegiatanp);
        ImageView img_kg = findViewById(R.id.thumbnaildkp);
        //TextView tv_dscb_kg = findViewById(R.id.ddp);

//        String Bold = "gotham_black.ttf";
//        Typeface typefacebold = Typeface.createFromAsset(getAssets(), Bold);
//        tv_nama_kg.setTypeface(typefacebold);
//        tv_tgl_kg.setTypeface(typefacebold);
//        tv_tmpt_kg.setTypeface(typefacebold);
//        tv_dscb_kg.setTypeface(typefacebold);
//
//        String Normal = "GOTHAM-LIGHT.TTF";
//        Typeface typefacenormal = Typeface.createFromAsset(getAssets(), Normal);
//        tv_dsc_kg.setTypeface(typefacenormal);
//
//        // setting values to each view
//
        tv_nama_kg.setText(nama_kegiatan);
        tv_tgl_kg.setText(tanggal_kegiatan);
        tv_tmpt_kg.setText(tempat_kegiatan);
        tv_dsc_kg.setText(deskripsi_kegiatan);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);

        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" +foto_kegiatan;
        // set image using Glide
        Glide.with(this).load(fullUrl).apply(requestOptions).into(img_kg);
//
//        Picasso.with(this)
//                .load(fullUrl)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.drawable.loading)
//                .into(img_kg);

    }
}
