package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class berita_detail_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarid);
        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        String judul_berita  = getIntent().getExtras().getString("judul_berita");
        String tgl_berita = getIntent().getExtras().getString("tanggal_berita");
        String tempat_berita = getIntent().getExtras().getString("lokasi_berita") ;
        final String deskripsi_berita = getIntent().getExtras().getString("deskripsi_berita");
        final String foto_berita = getIntent().getExtras().getString("foto_berita") ;


        // ini views
        TextView tv_nama_br = findViewById(R.id.judul_berita);
        TextView tv_tgl_br = findViewById(R.id.tanggal_berita);
        TextView tv_tmpt_br = findViewById(R.id.lokasi_berita) ;
        //TextView tv_des = findViewById(R.id.des);
        final TextView tv_dsc_br = findViewById(R.id.deskripsi_berita);
        final ImageView img_br = findViewById(R.id.thumbnailbr);

        tv_nama_br.setText(judul_berita);
        tv_tgl_br.setText(tgl_berita);
        tv_tmpt_br.setText(tempat_berita);
        tv_dsc_br.setText(deskripsi_berita);

        //RequestOptions requestOptions = new RequestOptions()
              //  .centerCrop()
                //.placeholder(R.drawable.loading)
               // .error(R.drawable.loading);

        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/berita/" +foto_berita;
        Picasso.with(this)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.loading)
                .into(img_br);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = tv_dsc_br.getText().toString();
                Uri pictureUri = Uri.parse("http://appro.probolinggokab.go.id/adminhumas/pict/berita/" +foto_berita);
                //String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/berita/" +foto_berita.toString();

                Intent i= new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Berita");
                i.putExtra(Intent.EXTRA_TITLE, "Beritax0");
                i.putExtra(Intent.EXTRA_TEXT, status);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_STREAM, pictureUri);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(Intent.createChooser(i,"Share"));
            }
        });

    }


}
