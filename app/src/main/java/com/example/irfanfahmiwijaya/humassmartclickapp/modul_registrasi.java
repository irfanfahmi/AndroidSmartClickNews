package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.irfanfahmiwijaya.humassmartclickapp.Adapter.MyCommand;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class modul_registrasi extends AppCompatActivity {
    ProgressDialog dialogLoading;
    Button btnregister;
    EditText Enamalengkap,Enohp,Eusername,Epassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_registrasi);

        Enamalengkap = (EditText)findViewById(R.id.nama_lengkap);
        Enohp = (EditText) findViewById(R.id.no_hp);
        Eusername = (EditText)findViewById(R.id.username);
        Epassword = (EditText)findViewById(R.id.password);
        btnregister = (Button)findViewById(R.id.btn_register);

        final MyCommand myCommand = new MyCommand(getApplicationContext());

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String nama = Enamalengkap.getText().toString();
                final String nohp = Enohp.getText().toString();
                final String password = Epassword.getText().toString();
                final String username = Eusername.getText().toString();
                if (!validasi() ) {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }else {

                    dialogLoading = ProgressDialog.show(modul_registrasi.this, "",
                            "Loading. Please wait...", true);


                    //String url = "http://192.168.1.15/imageuploadtest/Registrasikontributor.php";
                    //String url = "http://muslim.labsi-telkomuniversity.com/api/kontributor/regis";
                    String url = "http://appro.probolinggokab.go.id/adminhumas/registrasi.php";


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        final JSONObject jsonObject = new JSONObject(response);
                                        String status = jsonObject.getString("status");
                                        if (status.equals("success")) {



                                        } else {

                                            Toast.makeText(getApplicationContext(),
                                                    jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        }

                                        dialogLoading.hide();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        dialogLoading.dismiss();
                                    }
                                    Intent i = new Intent(modul_registrasi.this,modul_login.class);
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Terimakasih Sudah Registrasi", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Siahkan Login Kembali", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(),
                                        "Koneksi Timeout , Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(),
                                        "Server Mengalami Masalah , Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(),
                                        "Jaringan Mengalami Masalah, Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "Registrasi Gagal , Silahkan Coba Kembali", Toast.LENGTH_SHORT).show();
                            }
                            dialogLoading.hide();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("nama_user",nama);
                            params.put("nohp_user",nohp);
                            params.put("username",username);
                            params.put("password",password);

                            Log.d("Response", String.valueOf(params));
                            return params;
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton2.getmInstance(getBaseContext()).addToRequestque(stringRequest);
                }
                myCommand.execute();



            }
        });


    }

    public boolean validasi() {
        boolean valid = true;

        final String nama = Enamalengkap.getText().toString();
        final String nohp = Enohp.getText().toString();
        final String username = Eusername.getText().toString();
        final String password = Epassword.getText().toString();

        if (nama.isEmpty()) {
            Enamalengkap.setError("Nama Lengkap Harus Terisi");
            valid = false;
        } else {
            Enamalengkap.setError(null);
        }

        if (nohp.isEmpty()) {
            Enohp.setError("No Hp Harus Terisi");
            valid = false;
        } else {
            Enohp.setError(null);
        }
        if (username.isEmpty()) {
            Eusername.setError("Username Harus Terisi");
            valid = false;
        } else {
            Eusername.setError(null);
        }
        if (password.isEmpty()) {
            Epassword.setError("Password Harus Terisi");
            valid = false;
        } else {
            Epassword.setError(null);
        }
        return valid;
    }
}
