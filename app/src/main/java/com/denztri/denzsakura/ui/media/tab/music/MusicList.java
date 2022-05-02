package com.denztri.denzsakura.ui.media.tab.music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicList {
    @SerializedName("albumName")
    @Expose
    private String albumName;
    @SerializedName("artist")
    @Expose
    private List<String> artist = null;
    @SerializedName("images")
    @Expose
    private List<MusicImagesList> images = null;
    @SerializedName("song")
    @Expose
    private String song;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public List<MusicImagesList> getImages() {
        return images;
    }

    public void setImages(List<MusicImagesList> images) {
        this.images = images;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

}

