package com.denztri.denzsakura.ui.media.tab.music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denztri.denzsakura.R;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicListHolder> {
    private final Context context;
    private List<MusicList> musicLists;
    private MediaPlayer mediaPlayer;
    private int playPos;
    private MusicListAdapter.MusicListHolder playinHolder;

    @SuppressLint("NotifyDataSetChanged")
    public void setMusicLists(List<MusicList> musicLists) {
        this.musicLists = musicLists;
        playPos = -1;
        notifyDataSetChanged();
    }

    public MusicListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MusicListAdapter.MusicListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_music_row, parent, false);

        return new MusicListAdapter.MusicListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.MusicListHolder holder, int position) {
        String url = this.musicLists.get(position).getImages().get(0).getUrl();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .fitCenter()
                .centerCrop()
                .fallback(R.drawable.ic_baseline_broken_image_24)
                .into(holder.img);
        List<String> artistList = musicLists.get(position).getArtist();
        holder.artist.setText(artistList.toString().substring(1,artistList.toString().length() - 1));
        holder.title.setText(musicLists.get(position).getSong());
        if (position == playPos){
            playinHolder = holder;
            updatePlayingView();
        } else {
          updateNonPlayingView(holder);
        }
    }

    @Override
    public void onViewRecycled(@NonNull MusicListHolder holder) {
        super.onViewRecycled(holder);
        if (playPos == holder.getBindingAdapterPosition()){
            updateNonPlayingView(playinHolder);
            playinHolder = null;
        }
    }

    private void updateNonPlayingView(MusicListHolder holder) {
        holder.playBtn.setIconResource(R.drawable.ic_baseline_play_arrow_24);
    }

    private void updatePlayingView() {
        if (mediaPlayer.isPlaying()){
            playinHolder.playBtn.setIconResource(R.drawable.ic_baseline_pause_24);
        } else {
            playinHolder.playBtn.setIconResource(R.drawable.ic_baseline_play_arrow_24);
        }
    }

    @Override
    public int getItemCount() {
        if (musicLists != null){
            return this.musicLists.size();
        }else {
            return 0;
        }
    }

    void stopPlayer(){
        if (mediaPlayer != null){
            releaseMediaPlayer();
        }
    }

    public class MusicListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView title;
        TextView artist;
        MaterialButton playBtn;


        public MusicListHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.music_img);
            title = itemView.findViewById(R.id.music_title);
            artist = itemView.findViewById(R.id.music_artist);
            playBtn = itemView.findViewById(R.id.music_btn_play);
            playBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getBindingAdapterPosition() == playPos){
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            } else {
                playPos = getBindingAdapterPosition();
                if (mediaPlayer != null){
                    if (playinHolder != null){
                        updateNonPlayingView(playinHolder);
                    }

                    mediaPlayer.release();
                }

                playinHolder = this;
                starMediaPlayer(musicLists.get(playPos).getPreviewUrl());
            }
            updatePlayingView();
        }
    }

    private void starMediaPlayer(String url){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(mediaPlayer -> releaseMediaPlayer());
        mediaPlayer.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.start();
            updatePlayingView();
        });
    }

    private void releaseMediaPlayer(){
        if (playinHolder != null){
            updateNonPlayingView(playinHolder);
        }
        mediaPlayer.release();
        mediaPlayer = null;
        playPos = -1;
    }
}
