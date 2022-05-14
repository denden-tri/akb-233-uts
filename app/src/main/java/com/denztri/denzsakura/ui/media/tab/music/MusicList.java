package com.denztri.denzsakura.ui.media.tab.music;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

@Entity
public class MusicList {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "albumName")
    @SerializedName("albumName")
    @Expose
    private String albumName;

    @ColumnInfo(name = "artist")
    @SerializedName("artist")
    @Expose
    private List<String> artist = null;

    @ColumnInfo(name = "images")
    @SerializedName("images")
    @Expose
    private List<MusicImagesList> images = null;

    @ColumnInfo(name = "song")
    @SerializedName("song")
    @Expose
    private String song;

    @ColumnInfo(name = "previewUrl")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

