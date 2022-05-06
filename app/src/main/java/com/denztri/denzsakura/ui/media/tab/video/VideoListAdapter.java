package com.denztri.denzsakura.ui.media.tab.video;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.denztri.denzsakura.R;
import com.denztri.denzsakura.db.Video;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {
    private List<Video> videoList;
    private final Lifecycle lifecycle;

    public VideoListAdapter( Lifecycle lifecycle, List<Video> list) {
        this.lifecycle = lifecycle;
        this.videoList = list;
    }


    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoListAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycle_video_row, parent, false);
        lifecycle.addObserver(youTubePlayerView);

        return new VideoListAdapter.VideoViewHolder(youTubePlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.VideoViewHolder holder, int position) {
        holder.cueVideo(videoList.get(position).videoId);
    }

    @Override
    public int getItemCount() {
        if (videoList != null){
            return videoList.size();
        }else {
            return 2;
        }
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        private YouTubePlayer youTubePlayer;
        private String currentVideoId;

        public VideoViewHolder(@NonNull YouTubePlayerView playerView) {
            super(playerView);

            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initYouTubePlayer) {
                    youTubePlayer = initYouTubePlayer;
                    if (currentVideoId.equals("dQw4w9WgXcQ")) {
                        youTubePlayer.loadVideo(currentVideoId, 0);
                    }else {
                        youTubePlayer.cueVideo(currentVideoId, 0);
                    }
                }
            });
        }

        public void cueVideo(String videoId){
            currentVideoId = videoId;

            if (youTubePlayer == null){
                return;
            }

            youTubePlayer.cueVideo(videoId, 0);
        }
    }
}
