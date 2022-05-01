package com.denztri.denzsakura.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "video_id")
    public String videoId;

    public Video(String videoId) {
        this.videoId = videoId;
    }

    public static Video[] populateData(){
        return new Video[]{
                new Video("G8GaQdW2hWc"),
                new Video("dQw4w9WgXcQ")
        };
    }
}
