package com.denztri.denzsakura.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoDao {
    @Query("SELECT * FROM video")
    LiveData<List<Video>> getAllVideosId();

    @Insert()
    void insert(Video... videos);

    @Query("DELETE FROM video")
    void deleteAll();
}
