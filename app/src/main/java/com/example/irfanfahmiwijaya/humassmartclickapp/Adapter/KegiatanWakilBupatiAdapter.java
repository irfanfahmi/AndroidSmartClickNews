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

import com.example.irfanfahmiwijaya.humassmartclickapp.Model.KegiatanWakilBupati;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.example.irfanfahmiwijaya.humassmartclickapp.wakil_bupati_detail_item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KegiatanWakilBupatiAdapter extends RecyclerView.Adapter<KegiatanWakilBupatiAdapter.MyViewHolder> {

    private Context Mctx;
    private ArrayList<KegiatanWakilBupati> kgtnWakilBupatilist;


    public KegiatanWakilBupatiAdapter(Context Mctx, ArrayList<KegiatanWakilBupati> kgtnWakilBupatilist) {
        this.Mctx = Mctx;
        this.kgtnWakilBupatilist = kgtnWakilBupatilist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.wakil_bupati_item_row, parent, false);


        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
        }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final KegiatanWakilBupati infow = kgtnWakilBupatilist.get(position);
        holder.vh_nama_kegiatanw.setText(infow.nama_kegiatan_w);
        holder.vh_tanggal_kegiatanw.setText(infow.tanggal_kegiatan_w);
        holder.vh_tempat_kegiatanw.setText(infow.tempat_kegiatan_w);
        //holder.vh_deskripsi_berita.setText(info.deskripsi_berita);
        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" + infow.foto_kegiatan_w;
        Picasso.with(Mctx)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.loading)
                .into(holder.fotoThumbnailkw);
//        Toast toast = Toast.makeText(Mctx,infob.tanggal_kegiatan_b, Toast.LENGTH_LONG);
//        toast.show();
        holder.view_containerw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mctx, wakil_bupati_detail_item.class);
                i.putExtra("nama_kegiatanw", infow.nama_kegiatan_w);
                i.putExtra("tanggal_kegiatanw", infow.tanggal_kegiatan_w);
                i.putExtra("tempat_kegiatanw", infow.tempat_kegiatan_w);
                i.putExtra("deskripsi_kegiatanw", infow.deskripsi_kegiatan_w);
                i.putExtra("foto_kegiatanw", infow.foto_kegiatan_w);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mctx.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (kgtnWakilBupatilist != null) {
            return kgtnWakilBupatilist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vh_nama_kegiatanw,vh_tanggal_kegiatanw,vh_tempat_kegiatanw;
        ImageView fotoThumbnailkw;
        CardView view_containerw;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_containerw = itemView.findViewById(R.id.containerw);
            vh_nama_kegiatanw = itemView.findViewById(R.id.nama_kegiatanw);
            vh_tanggal_kegiatanw = itemView.findViewById(R.id.tanggal_kegiatanw);
            vh_tempat_kegiatanw = itemView.findViewById(R.id.tempat_kegiatanw);

            fotoThumbnailkw = itemView.findViewById(R.id.thumbnailkw);
        }
    }

}
