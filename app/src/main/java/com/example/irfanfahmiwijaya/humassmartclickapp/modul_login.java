package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class modul_login extends AppCompatActivity {

    EditText Eusername,Epassword;
    TextView Treg;
    Button btnlogin;
    ProgressDialog dialogLoading;
    SharedPreferences sharedP;
    SharedPreferences.Editor shareEdit;
    final String PREF = "Keypref";
    final String KEY_NAME = "nama_user";
    final String KEY_USER = "id_user";
    private long backPres;
    private Toast backToast;
    //public static final  String NUSER = "nama_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_login);

         btnlogin = (Button) findViewById(R.id.btn_login);
         Eusername = (EditText) findViewById(R.id.e_username);
         Epassword = (EditText) findViewById(R.id.e_password);
         Treg = (TextView) findViewById(R.id.t_reg);

         Treg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent reg = new Intent(modul_login.this,modul_registrasi.class);
                 startActivity(reg);
             }
         });


        if (getprefname().length() != 0) {
            Intent kliklangsung = new Intent(modul_login.this,MainActivity.class);
            startActivity(kliklangsung);
        } else {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(modul_login.this);
//            builder.setMessage("Anda harus login terlebih dahulu untuk masuk ke fitur ini !")
//                    .setNegativeButton("Tutup",null)
//                    .setPositiveButton("Login", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Intent login = new Intent(modul_login.this,LoginActivity.class);
//                            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(login);
//                        }
//                    f});
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();

        }



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user = Eusername.getText().toString();
                final String pass = Epassword.getText().toString();

                if (!validasi()) {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();


                } else {
                    dialogLoading = ProgressDialog.show(modul_login.this, "",
                            "Loading. Please wait...", true);


                    String url = "http://appro.probolinggokab.go.id/adminhumas/login.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.contains("Login Failed")){
                                Toast.makeText(getApplicationContext(), " Login Gagal, Coba kembali !", Toast.LENGTH_SHORT).show();
                                dialogLoading.hide();
                            }else {

                                String data = new String(response);
                                String[] separated = data.split(",");
                                shareEdit.putString(KEY_USER, separated[0]);
                                shareEdit.putString(KEY_NAME, separated[1]);
                            shareEdit.apply();
                            startActivity(new Intent(modul_login.this, MainActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Selamat Datang "+getprefname().toString(), Toast.LENGTH_SHORT).show();
//
//
                            dialogLoading.hide();

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(),
                                        "Periksa Koneksi Anda dan Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(),
                                        "Server Mengalami Masalah , Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(),
                                        "Jaringan Mengalami Masalah, Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof AuthFailureError) {
                                Toast.makeText(getApplicationContext(),
                                        "Username atau Password Tidak tersedia, Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Login Gagal , Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            }
                            dialogLoading.hide();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> data = new HashMap<>();
                            data.put("username", user);
                            data.put("password", pass);
                            return data;
                        }
                    };
                    //MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton2.getmInstance(getBaseContext()).addToRequestque(stringRequest);
                }

            }
        });

        sharedP = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        shareEdit = sharedP.edit();
    }

    public String getprefname(){
        sharedP = getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedP.getString(KEY_NAME,"");

    }

    public boolean validasi() {
        boolean valid = true;

        final String email = Eusername.getText().toString();
        final String pass = Epassword.getText().toString();

        if (email.isEmpty()) {
            Eusername.setError("Username Harus Terisi");
            valid = false;
        } else {
            Eusername.setError(null);
        }

        if (pass.isEmpty()) {
            Epassword.setError("Password Harus Terisi");
            valid = false;
        } else {
            Epassword.setError(null);
        }
        return valid;

    }

    @Override
    public void onBackPressed() {


        if(backPres + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.finish();
            return;
        }else {
            backToast =  Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPres = System.currentTimeMillis();
    }
}
