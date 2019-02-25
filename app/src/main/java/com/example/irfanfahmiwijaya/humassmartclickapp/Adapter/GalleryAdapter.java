package com.example.irfanfahmiwijaya.humassmartclickapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfanfahmiwijaya.humassmartclickapp.Model.GalleryKegiatan;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private Context Mctx;
    private ArrayList<GalleryKegiatan> kgtnGallerylist;
    //tes memakai data bupati


    public GalleryAdapter(Context Mctx, ArrayList<GalleryKegiatan> kgtnGallerylist) {
        this.Mctx = Mctx;
        this.kgtnGallerylist = kgtnGallerylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.gallery_item_row, parent, false);


        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
        }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final GalleryKegiatan infob = kgtnGallerylist.get(position);
        holder.vh_nama_fotob.setText(infob.nama_kegiatan_b);


        //holder.vh_deskripsi_berita.setText(info.deskripsi_berita);
        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" + infob.foto_kegiatan_b;
        Picasso.with(Mctx)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.loading)
                .into(holder.img_galleryb);


//        Toast toast = Toast.makeText(Mctx,infob.foto_kegiatan_b, Toast.LENGTH_LONG);
//        toast.show();
//        holder.view_containerb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Mctx, bupati_detail_item.class);
//                i.putExtra("nama_kegiatanb", infob.nama_kegiatan_b);
//                i.putExtra("tanggal_kegiatanb", infob.tanggal_kegiatan_b);
//                i.putExtra("tempat_kegiatanb", infob.tempat_kegiatan_b);
//                i.putExtra("deskripsi_kegiatanb", infob.deskripsi_kegiatan_b);
//                i.putExtra("foto_kegiatanb", infob.foto_kegiatan_b);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Mctx.startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        if (kgtnGallerylist != null) {
            return kgtnGallerylist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vh_nama_fotob;
        ImageView img_galleryb;
        CardView view_containerb;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_containerb = itemView.findViewById(R.id.containerb);
            vh_nama_fotob = itemView.findViewById(R.id.nama_fotob);
            img_galleryb = itemView.findViewById(R.id.img_galleryb);

        }
    }

}
