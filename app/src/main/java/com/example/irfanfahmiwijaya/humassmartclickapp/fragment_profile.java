package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class fragment_profile extends Fragment {

    private RecyclerView rview ;
    private TextView namauser;
    private Button btnlogout;
    SharedPreferences sharedP;
    SharedPreferences.Editor shareEdit;
    final String PREF = "Keypref";
    final String KEY_NAME = "nama_user";
    final String KEY_USER = "id_user";
    public static final  String NUSER = "nama_user";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        namauser = view.findViewById(R.id.t_namauser);
        btnlogout = view.findViewById(R.id.btn_logout);

        namauser.setText(getprefname().toString());

        sharedP = getActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        shareEdit = sharedP.edit();

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareEdit.clear();
                shareEdit.commit();

                // After logout redirect user to Loing Activity
                Intent i = new Intent(getActivity(), modul_login.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                getActivity().startActivity(i);
            }
        });

     return view;
    }

    public String getprefname(){
        sharedP = getActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedP.getString(KEY_NAME,"");

    }
}
