package com.denztri.denzsakura.ui.media.tab.music;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

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
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}

