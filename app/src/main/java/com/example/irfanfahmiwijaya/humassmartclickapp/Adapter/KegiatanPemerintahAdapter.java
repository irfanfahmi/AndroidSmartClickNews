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

import com.example.irfanfahmiwijaya.humassmartclickapp.pemerintah_detail_item;
import com.example.irfanfahmiwijaya.humassmartclickapp.Model.KegiatanPemerintah;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class KegiatanPemerintahAdapter extends RecyclerView.Adapter<KegiatanPemerintahAdapter.MyViewHolder> {

    private Context Mctx;
    private ArrayList<KegiatanPemerintah> kgtnPemerintahlist;


    public KegiatanPemerintahAdapter(Context Mctx, ArrayList<KegiatanPemerintah> kgtnPemerintahlist) {
        this.Mctx = Mctx;
        this.kgtnPemerintahlist = kgtnPemerintahlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.pemerintah_item_row, parent, false);
//
//        String customFont = "gotham_black.ttf";
//        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), customFont);
//        String Normal = "GOTHAM-LIGHT.TTF";
//        Typeface typefacenormal5 = Typeface.createFromAsset(view.getContext().getAssets(), Normal);
//
//        TextView textView1 = (TextView) view.findViewById(R.id.nama_kegiatanp);
//        TextView textView2 = (TextView) view.findViewById(R.id.tgp);
//        TextView textView3 = (TextView) view.findViewById(R.id.tpp);
//        TextView textView4 = (TextView) view.findViewById(R.id.tanggal_kegiatanp);
//        TextView textView5 = (TextView) view.findViewById(R.id.tempat_kegiatanp);
//
//
//        textView1.setTypeface(typeface);
//        textView2.setTypeface(typeface);
//        textView3.setTypeface(typeface);
//        textView4.setTypeface(typefacenormal5);
//        textView5.setTypeface(typefacenormal5);


        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
        }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final KegiatanPemerintah infop = kgtnPemerintahlist.get(position);
        holder.vh_nama_kegiatanp.setText(infop.nama_kegiatan_p);
        holder.vh_tanggal_kegiatanp.setText(infop.tanggal_kegiatan_p);
        holder.vh_tempat_kegiatanp.setText(infop.tempat_kegiatan_p);
        //holder.vh_deskripsi_berita.setText(info.deskripsi_berita);
        String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/" + infop.foto_kegiatan_p;
        Picasso.with(Mctx)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.loading)
                .into(holder.fotoThumbnailkp);
//        Toast toast = Toast.makeText(Mctx,infob.tanggal_kegiatan_b, Toast.LENGTH_LONG);
//        toast.show();
        holder.view_containerp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mctx, pemerintah_detail_item.class);
                i.putExtra("nama_kegiatanp", infop.nama_kegiatan_p);
                i.putExtra("tanggal_kegiatanp", infop.tanggal_kegiatan_p);
                i.putExtra("tempat_kegiatanp", infop.tempat_kegiatan_p);
                i.putExtra("deskripsi_kegiatanp", infop.deskripsi_kegiatan_p);
                i.putExtra("foto_kegiatanp", infop.foto_kegiatan_p);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mctx.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (kgtnPemerintahlist != null) {
            return kgtnPemerintahlist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vh_nama_kegiatanp,vh_tanggal_kegiatanp,vh_tempat_kegiatanp;
        ImageView fotoThumbnailkp;
        CardView view_containerp;


        public MyViewHolder(View itemView) {
            super(itemView);
            view_containerp = itemView.findViewById(R.id.containerp);
            vh_nama_kegiatanp = itemView.findViewById(R.id.nama_kegiatanp);
            vh_tanggal_kegiatanp = itemView.findViewById(R.id.tanggal_kegiatanp);
            vh_tempat_kegiatanp = itemView.findViewById(R.id.tempat_kegiatanp);

            fotoThumbnailkp = itemView.findViewById(R.id.thumbnailkp);
        }
    }

}
