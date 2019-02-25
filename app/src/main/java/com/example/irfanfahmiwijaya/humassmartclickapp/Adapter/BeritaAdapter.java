package com.example.irfanfahmiwijaya.humassmartclickapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfanfahmiwijaya.humassmartclickapp.Model.Berita;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.example.irfanfahmiwijaya.humassmartclickapp.berita_detail_item;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {

    private Context Mctx;
    private ArrayList<Berita> beritalist;
    private Uri uri;
    int currentPos = 0;
    public BeritaAdapter(Context Mctx, ArrayList<Berita> beritalist) {
        this.Mctx = Mctx;
        this.beritalist = beritalist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.berita_item_row, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BeritaAdapter.MyViewHolder holder, int position) {

        MyViewHolder viewHolder = (MyViewHolder) holder;

        final Berita info = beritalist.get(position);
        holder.vh_judul_berita.setText(info.judul_berita);
//        holder.vh_tanggal_berita.setText(info.tgl_kegiatan);
//        holder.vh_lokasi_berita.setText(info.tempat_kegiatan);
        holder.vh_deskripsi_berita.setText(info.deskripsi_berita);
        final String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/berita/" + info.foto_berita;


        Picasso.with(Mctx)
                .load(fullUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_image_black_24dp)
                .into(holder.fotoThumbnailberita);

//
//        if(null != info.foto_berita) {
//            uri = Uri.parse(fullUrl);
//            viewHolder.fotoThumbnailberita.setImageURI(uri);
//            viewHolder.fotoThumbnailberita.setVisibility(View.VISIBLE);
////            Picasso.with(Mctx)
////                    .load(fullUrl)
////                    .placeholder(R.mipmap.ic_launcher)
////                    .error(R.drawable.ic_image_black_24dp)
////                    .into(holder.fotoThumbnailberita);
//
//            Toast.makeText(Mctx, info.foto_berita, Toast.LENGTH_SHORT).show();
//
//        }else{
//
//            viewHolder.videoThumbnailberita.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//             @Override
//             public void onCompletion(MediaPlayer mp) {
//                // String uriPath = "https://....."; // your URL here
//                 uri = Uri.parse(fullUrl);
//                 MediaPlayer mediaPlayer = new MediaPlayer();
//                 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                 try {
//                     mediaPlayer.setDataSource(String.valueOf(uri));
//                     mediaPlayer.prepareAsync(); // might take
//                     mediaPlayer.start();
//
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//            });
//        }








        holder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mctx, berita_detail_item.class);
                i.putExtra("judul_berita", info.judul_berita);
                i.putExtra("tanggal_berita", info.tanggal_berita);
                i.putExtra("lokasi_berita", info.lokasi_berita);
                i.putExtra("deskripsi_berita", info.deskripsi_berita);
                i.putExtra("foto_berita", info.foto_berita);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mctx.startActivity(i);
            }
        });

        holder.v_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/berita/" + info.foto_berita;
                shareItem(fullUrl);
            }





        });

    }

    private void shareItem(String url) {
        Picasso.with(Mctx.getApplicationContext()).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mctx.startActivity(Intent.createChooser(i, "Share Image"));
            }
            @Override
            public void onBitmapFailed(Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
    }

    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(Mctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }




    @Override
    public int getItemCount() {
        if (beritalist != null) {
            return beritalist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vh_judul_berita,vh_deskripsi_berita;
        ImageView fotoThumbnailberita,v_share;
        CardView view_container;
        //VideoView videoThumbnailberita;


        public MyViewHolder(View itemView) {
            super(itemView);
            v_share = itemView.findViewById(R.id.ic_share);
            view_container = itemView.findViewById(R.id.contain1);
            vh_judul_berita = itemView.findViewById(R.id.judul);
            vh_deskripsi_berita = itemView.findViewById(R.id.dscb);
            fotoThumbnailberita = itemView.findViewById(R.id.thumbnailb);
           // videoThumbnailberita = itemView.findViewById(R.id.thumbnailbVid);

        }
    }
}
