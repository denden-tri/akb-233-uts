package com.denztri.denzsakura.ui.media.tab.music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denztri.denzsakura.R;

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

    @SuppressLint("NotifyDataSetChanged")
    public void setMusicLists(List<MusicList> musicLists) {
        this.musicLists = musicLists;
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
    }

    @Override
    public int getItemCount() {
        if (musicLists != null){
            return this.musicLists.size();
        }else {
            return 0;
        }
    }

    public static class MusicListHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView artist;

        public MusicListHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.music_img);
            title = itemView.findViewById(R.id.music_title);
            artist = itemView.findViewById(R.id.music_artist);
        }
    }
}
