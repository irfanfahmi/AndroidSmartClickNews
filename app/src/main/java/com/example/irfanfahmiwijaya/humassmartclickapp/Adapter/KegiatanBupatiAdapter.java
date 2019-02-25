package com.example.irfanfahmiwijaya.humassmartclickapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfanfahmiwijaya.humassmartclickapp.Model.KegiatanBupati;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.example.irfanfahmiwijaya.humassmartclickapp.bupati_detail_item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KegiatanBupatiAdapter extends RecyclerView.Adapter<KegiatanBupatiAdapter.MyViewHolder> {

    private Context Mctx;
    private ArrayList<KegiatanBupati> kgtnBupatilist;


    public KegiatanBupatiAdapter(Context Mctx, ArrayList<KegiatanBupati> kgtnBupatilist) {
        this.Mctx = Mctx;
        this.kgtnBupatilist = kgtnBupatilist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.bupati_item_row, parent, false);

//        String customFont3 = "gotham_black.ttf";
//        Typeface typeface3 = Typeface.createFromAsset(view.getContext().getAssets(), customFont3);
//        String customFont4 = "gotham_black.ttf";
//        Typeface typeface4 = Typeface.createFromAsset(view.getContext().getAssets(), customFont4);
//        String customFont5 = "gotham_black.ttf";
//        Typeface typeface5 = Typeface.createFromAsset(view.getContext().getAssets(), customFont5);
//        String Normal = "GOTHAM-LIGHT.TTF";
//        Typeface typefacenormal5 = Typeface.createFromAsset(view.getContext().getAssets(), Normal);

//        TextView textView3 = (TextView) view.findViewById(R.id.tglb);
//        TextView textView4 = (TextView) view.findViewById(R.id.tpb);
//        TextView textView5 = (TextView) view.findViewById(R.id.nama_kegiatanb);
//        TextView textView6 = (TextView) view.findViewById(R.id.tanggal_kegiatanb);
//        TextView textView7 = (TextView) view.findViewById(R.id.tempat_kegiatanb);
//        textView3.setTypeface(typeface3);
//        textView4.setTypeface(typeface4);
//        textView5.setTypeface(typeface5);
//        textView6.setTypeface(typefacenormal5);
//        textView7.setTypeface(typefacenormal5);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
        }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final KegiatanBupati infob = kgtnBupatilist.get(position);
        holder.vh_nama_kegiatanb.setText(infob.nama_kegiatan_b);
        holder.vh_tanggal_kegiatanb.setText(infob.tanggal_kegiatan_b);
        holder.vh_tempat_kegiatanb.setText(infob.tempat_kegiatan_b);
        //holder.vh_deskripsi_berita.setText(info.deskripsi_berita);
        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" + infob.foto_kegiatan_b;
        Picasso.with(Mctx)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.loading)
                .into(holder.fotoThumbnailkb);
//        Toast toast = Toast.makeText(Mctx,infob.foto_kegiatan_b, Toast.LENGTH_LONG);
//        toast.show();
        holder.view_containerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mctx, bupati_detail_item.class);
                i.putExtra("nama_kegiatanb", infob.nama_kegiatan_b);
                i.putExtra("tanggal_kegiatanb", infob.tanggal_kegiatan_b);
                i.putExtra("tempat_kegiatanb", infob.tempat_kegiatan_b);
                i.putExtra("deskripsi_kegiatanb", infob.deskripsi_kegiatan_b);
                i.putExtra("foto_kegiatanb", infob.foto_kegiatan_b);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mctx.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (kgtnBupatilist != null) {
            return kgtnBupatilist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vh_nama_kegiatanb,vh_tanggal_kegiatanb,vh_tempat_kegiatanb;
        ImageView fotoThumbnailkb;
        CardView view_containerb;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_containerb = itemView.findViewById(R.id.containerb);
            vh_nama_kegiatanb = itemView.findViewById(R.id.nama_kegiatanb);
            vh_tanggal_kegiatanb = itemView.findViewById(R.id.tanggal_kegiatanb);
            vh_tempat_kegiatanb = itemView.findViewById(R.id.tempat_kegiatanb);

            fotoThumbnailkb = itemView.findViewById(R.id.thumbnailkb);
        }
    }

}
