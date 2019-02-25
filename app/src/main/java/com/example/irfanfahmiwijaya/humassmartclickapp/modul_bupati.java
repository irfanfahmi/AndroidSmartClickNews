package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.irfanfahmiwijaya.humassmartclickapp.Adapter.KegiatanBupatiAdapter;
import com.example.irfanfahmiwijaya.humassmartclickapp.Adapter.MySingleton;
import com.example.irfanfahmiwijaya.humassmartclickapp.Model.KegiatanBupati;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

public class modul_bupati extends AppCompatActivity {
    private RecyclerView rview ;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modul_bupati);

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

        rview = findViewById(R.id.recylcerViewb);
        rview.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        rview.setLayoutManager(manager);

        String url = "http://appro.probolinggokab.go.id/adminhumas/tampil_kegiatan_bupati.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<KegiatanBupati> kegiatanbupatilist = new JsonConverter<KegiatanBupati>().toArrayList(response, KegiatanBupati.class);
//                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"List Kegiatan", Toast.LENGTH_LONG);
//                toast.show();
                KegiatanBupatiAdapter adapter = new KegiatanBupatiAdapter(getApplicationContext(), kegiatanbupatilist);
                rview.setLayoutManager(manager);
                rview.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                toast.show();

            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }
}
