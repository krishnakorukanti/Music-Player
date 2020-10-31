package com.crish.playmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crish.playmusic.Models.MusicFiles;
import com.crish.playmusic.PlayerActivity;
import com.crish.playmusic.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private Context mContext;
    private ArrayList<MusicFiles> mFiles;

    public MusicAdapter(Context mContext, ArrayList<MusicFiles> mFiles) {
        this.mContext = mContext;
        this.mFiles = mFiles;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_items,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.fileName.setText(mFiles.get(position).getTitle());
        byte[] image = getAlbumImg(mFiles.get(position).getPath());
        if (image!=null){
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.albumImg);
        }
        else {
            Glide.with(mContext).asBitmap()
                    .load(R.drawable.ic_music_notes)
                    .into(holder.albumImg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder{
        private TextView fileName;
        private ImageView albumImg;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.musicFileName);
            albumImg = itemView.findViewById(R.id.musicImg);
        }
    }

    private byte[] getAlbumImg(String uri){
        MediaMetadataRetriever  retriever = new MediaMetadataRetriever();

        try {
            retriever.setDataSource(uri);
            byte[] art  = retriever.getEmbeddedPicture();
            retriever.release();
            return art;
        }
        catch (Exception e){

        }
        return null;
    }
}
