package com.denztri.denzsakura.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.denztri.denzsakura.R;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class GalleryListAdapter extends RecyclerView.Adapter<GalleryListAdapter.GalleryViewHolder> {
    private final Context context;
    private List<GalleryList> galleryLists;

    public GalleryListAdapter(Context context){
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setGalleryLists(List<GalleryList> list){
        this.galleryLists = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryListAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_gallery_row, parent, false);

        return new GalleryListAdapter.GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryListAdapter.GalleryViewHolder holder, int position) {
        String url = this.galleryLists.get(position).getUrl();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_image_24)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .centerCrop()
                .fallback(R.drawable.ic_baseline_broken_image_24)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (galleryLists != null){
            return this.galleryLists.size();
        }else {
            return 0;
        }
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gallery_recycle_img);
        }
    }
}
