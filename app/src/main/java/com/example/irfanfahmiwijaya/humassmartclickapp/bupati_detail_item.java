package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class bupati_detail_item extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bupati_detail_item);

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

        String nama_kegiatan  = getIntent().getExtras().getString("nama_kegiatanb");
        String tanggal_kegiatan = getIntent().getExtras().getString("tanggal_kegiatanb");
        String tempat_kegiatan = getIntent().getExtras().getString("tempat_kegiatanb") ;
        String deskripsi_kegiatan = getIntent().getExtras().getString("deskripsi_kegiatanb");
        String foto_kegiatan = getIntent().getExtras().getString("foto_kegiatanb") ;


        // ini views
        TextView tv_nama_kg = findViewById(R.id.nama_dkegiatanb);
        TextView tv_tgl_kg = findViewById(R.id.tanggal_dkegiatanb);
        TextView tv_tmpt_kg = findViewById(R.id.tempat_dkegiatanb) ;
        TextView tv_dsc_kg = findViewById(R.id.deskripsi_dkegiatanb);
        //TextView tv_dscb_kg = findViewById(R.id.dskb);
        ImageView img_kg = findViewById(R.id.thumbnaildkb);

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
//                .error(R.drawable.logoapp)
//                .into(img_kg);

    }

}
