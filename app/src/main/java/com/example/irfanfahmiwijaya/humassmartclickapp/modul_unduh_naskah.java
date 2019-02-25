package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class modul_unduh_naskah extends AppCompatActivity {

    public class PDFDoc {
        public int getId_naskah() {
            return id_naskah;
        }

        public void setId_naskah(int id_naskah) {
            this.id_naskah = id_naskah;
        }

        public String getJudul_naskah() {
            return judul_naskah;
        }

        public void setJudul_naskah(String judul_naskah) {
            this.judul_naskah = judul_naskah;
        }

        public String getLokasi_file() {
            return lokasi_file;
        }

        public void setLokasi_file(String lokasi_file) {
            this.lokasi_file = lokasi_file;
        }

        int id_naskah;
        String judul_naskah;

        public PDFDoc() {
            this.id_naskah = id_naskah;
            this.judul_naskah = judul_naskah;
            this.lokasi_file = lokasi_file;
        }

        String lokasi_file;

    }
    /*
    Our custom adapter class
     */
    public class GridViewAdapter extends BaseAdapter {
        Context c;
        ArrayList<PDFDoc> pdfDocuments;

        public GridViewAdapter(Context c, ArrayList<PDFDoc> pdfDocuments) {
            this.c = c;
            this.pdfDocuments = pdfDocuments;
        }
        @Override
        public int getCount() {return pdfDocuments.size();}
        @Override
        public Object getItem(int pos) {return pdfDocuments.get(pos);}
        @Override
        public long getItemId(int pos) {return pos;}
        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.row_model,viewGroup,false);
            }

            TextView txtName = view.findViewById(R.id.pdfNameTxt);
//            TextView txtAuthor = view.findViewById(R.id.authorTxt);
            ImageView pdfIcon = view.findViewById(R.id.imageView);

            final PDFDoc pdf= (PDFDoc) this.getItem(pos);

            txtName.setText(pdf.getJudul_naskah());
//            txtAuthor.setText(pdf.getJudul_naskah());

//            if(pdf.getPdfURL() != null && pdf.getPdfURL().length()>0)
//            {
//                Picasso.get().load(pdf.getPdfIconURL()).placeholder(R.drawable.ic_image).into(pdfIcon);
//            }else {
//                Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
//                Picasso.get().load(R.drawable.pdf_icon).into(pdfIcon);
//            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(c, pdf.getJudul_naskah(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(c,PDFActivity.class);
                    i.putExtra("PATH",pdf.getLokasi_file());
                    c.startActivity(i);
                }
            });

            return view;
        }
    }
    /*
    Our HTTP Client
     */
    public class JSONDownloader {
        private static final String PDF_SITE_URL="http://appro.probolinggokab.go.id/adminhumas/pdfstar/";
        private final Context c;
        private GridViewAdapter adapter ;

        public JSONDownloader(Context c) {
            this.c = c;
        }
        /*
        DOWNLOAD PDFS FROM MYSQL
         */
        public void retrieve(final GridView gv, final ProgressBar myProgressBar)
        {
            final ArrayList<PDFDoc> pdfDocuments = new ArrayList<>();

            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.get(PDF_SITE_URL)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            PDFDoc p;
                            try
                            {
                                for(int i=0;i<response.length();i++)
                                {
                                    jo=response.getJSONObject(i);

                                    int id=jo.getInt("id_naskah");
                                    String judul=jo.getString("judul_naskah");
                                    //String description=jo.getString("description");
                                    String pdfURL=jo.getString("lokasi_file");
                                    //String pdfIconURL=jo.getString("pdf_icon_url");

                                    p=new PDFDoc();
                                    p.setId_naskah(id);
                                    p.setJudul_naskah(judul);
                                    //p.setCategory(category);
                                    //p.setCategory(description);
                                    p.setLokasi_file(PDF_SITE_URL+pdfURL);
                                    //p.setPdfIconURL(PDF_SITE_URL+pdfIconURL);

                                    pdfDocuments.add(p);
                                }
                                adapter =new GridViewAdapter(c,pdfDocuments);
                                gv.setAdapter(adapter);
                                myProgressBar.setVisibility(View.GONE);

                            }catch (JSONException e)
                            {
                                myProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                            myProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "UNSUCCESSFUL:ERROR IS :"+error.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul_unduh_naskah);

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

        final GridView myGridView= findViewById(R.id.myGridView);
        Button btnRetrieve= findViewById(R.id.downloadBtn);
        final ProgressBar myProgressBar= findViewById(R.id.myProgressBar);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloader(modul_unduh_naskah.this).retrieve(myGridView,myProgressBar);
            }
        });



    }
}
