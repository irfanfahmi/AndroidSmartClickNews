package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class wakil_bupati_detail_item extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakil_bupati_detail_item);
        toolbar = (Toolbar) findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String nama_kegiatan  = getIntent().getExtras().getString("nama_kegiatanw");
        String tanggal_kegiatan = getIntent().getExtras().getString("tanggal_kegiatanw");
        String tempat_kegiatan = getIntent().getExtras().getString("tempat_kegiatanw") ;
        String deskripsi_kegiatan = getIntent().getExtras().getString("deskripsi_kegiatanw");
        String foto_kegiatan = getIntent().getExtras().getString("foto_kegiatanw") ;


        // ini views
        TextView tv_nama_kg = findViewById(R.id.nama_dkegiatanw);
        TextView tv_tgl_kg = findViewById(R.id.tanggal_dkegiatanw);
        TextView tv_tmpt_kg = findViewById(R.id.tempat_dkegiatanw) ;
        TextView tv_dsc_kg = findViewById(R.id.deskripsi_dkegiatanw);
        ImageView img_kg = findViewById(R.id.thumbnaildkw);
        //TextView tv_dscw_kg = findViewById(R.id.ddw);

//        //customfont
//
//        String Bold = "gotham_black.ttf";
//        Typeface typefacebold = Typeface.createFromAsset(getAssets(), Bold);
//        tv_nama_kg.setTypeface(typefacebold);
//        tv_tgl_kg.setTypeface(typefacebold);
//        tv_tmpt_kg.setTypeface(typefacebold);
//        tv_dscw_kg.setTypeface(typefacebold);
//
//        String Normal = "GOTHAM-LIGHT.TTF";
//        Typeface typefacenormal = Typeface.createFromAsset(getAssets(), Normal);
//        tv_dsc_kg.setTypeface(typefacenormal);
//
//        // setting values to each view

        tv_nama_kg.setText(nama_kegiatan);
        tv_tgl_kg.setText(tanggal_kegiatan);
        tv_tmpt_kg.setText(tempat_kegiatan);
        tv_dsc_kg.setText(deskripsi_kegiatan);


        //RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);


        // set image using Glide
        // Glide.with(this).load(foto_kegiatan).apply(requestOptions).into(img_kg);
        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" +foto_kegiatan;
        Picasso.with(this)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.logoapp)
                .into(img_kg);


    }
}
