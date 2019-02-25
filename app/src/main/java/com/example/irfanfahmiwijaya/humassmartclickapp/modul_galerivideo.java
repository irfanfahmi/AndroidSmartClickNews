package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.irfanfahmiwijaya.humassmartclickapp.Adapter.GalleryVideoAdapter;
import com.example.irfanfahmiwijaya.humassmartclickapp.Adapter.MySingleton;
import com.example.irfanfahmiwijaya.humassmartclickapp.Model.GalleryVideo;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

public class modul_galerivideo extends AppCompatActivity {


    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://appro.probolinggokab.go.id/adminhumas/tampil_video_gallery.php";
    private ArrayList<GalleryVideo> GalleryVideolist;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private GalleryVideoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_galerivideo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbariv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewvideo);

        pDialog = new ProgressDialog(this);
        GalleryVideolist = new ArrayList<>();
        mAdapter = new GalleryVideoAdapter(getApplicationContext(), GalleryVideolist);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        //recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<GalleryVideo> GalleryVideolist = new JsonConverter<GalleryVideo>().toArrayList(response, GalleryVideo.class);
//                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"List Kegiatan", Toast.LENGTH_LONG);
//                toast.show();
                GalleryVideoAdapter adapter = new GalleryVideoAdapter(getApplicationContext(), GalleryVideolist);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);
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
