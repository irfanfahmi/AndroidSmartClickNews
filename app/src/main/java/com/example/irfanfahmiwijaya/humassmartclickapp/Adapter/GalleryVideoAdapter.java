package com.example.irfanfahmiwijaya.humassmartclickapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.irfanfahmiwijaya.humassmartclickapp.Model.GalleryVideo;
import com.example.irfanfahmiwijaya.humassmartclickapp.R;
import com.example.irfanfahmiwijaya.humassmartclickapp.video_detail_item;

import java.util.List;

public class GalleryVideoAdapter extends RecyclerView.Adapter<GalleryVideoAdapter.MyViewHolder> {

    private List<GalleryVideo> GalleryVideolist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public VideoView thumbnailv;
        public  LinearLayout kontentcard;
        //public Button namavideo;

        public MyViewHolder(View view) {
            super(view);
            thumbnailv = (VideoView) view.findViewById(R.id.thumbnailv);
            kontentcard = (LinearLayout) view.findViewById(R.id.kontencard);
            //namavideo = (Button) view.findViewById(R.id.nama_video);
        }


    }


    public GalleryVideoAdapter(Context context, List<GalleryVideo> GalleryVideolist) {
        mContext = context;
        this.GalleryVideolist = GalleryVideolist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_thumbnail_video, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final GalleryVideo video = GalleryVideolist.get(position);
        final String fullUrl = "http://appro.probolinggokab.go.id/adminhumas/pict/galeri_video/" + video.nama_file;


        try {
            Uri videoUri = Uri.parse(fullUrl);
            holder.thumbnailv.setVideoURI(videoUri);
            holder.thumbnailv.setTag(videoUri);
            //holder.thumbnailv.setMediaController(new MediaController(mContext));
            holder.thumbnailv.stopPlayback();
            //String hasVideo_string = (String) holder.thumbnailv.getTag();


        } catch (Exception e) {
            System.out.println("Error :" + e);
        }

        holder.thumbnailv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.pause();
            }
        });

        holder.thumbnailv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                holder.thumbnailv.setVideoPath(fullUrl.toString());
                holder.thumbnailv.start();
            }
        });



        holder.kontentcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, video_detail_item.class);
                i.putExtra("nama_file", video.nama_file);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);





            }
        });



//        Glide.with(mContext)
//                .load(fullUrl)
//                .thumbnail(0.5f)
//                .transition(new DrawableTransitionOptions().crossFade())
//                //.diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.thumbnailv);
    }

//    public void showDialog(Context mContext, String string){
//
//        final Dialog dialog = new Dialog(mContext);// add here your class name
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.activity_video_detail_item);//add your own xml with defied with and height of videoview
//        dialog.show();
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        dialog.getWindow().setAttributes(lp);
//        //uriPath= "android.resource://" + getPackageName() + "/" + R.raw.yourvid;
//
//        ((Activity) mContext).getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        Log.v("Vidoe-URI", fullUrl+ "");
//        holder.thumbnailv.setVideoURI(Uri.parse(fullUrl));
//        holder.thumbnailv.start();
//    }

    @Override
    public int getItemCount() {
        return GalleryVideolist.size();
    }






}
