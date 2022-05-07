package com.denztri.denzsakura.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 01-05-2022
 **/

@Dao
public interface VideoDao {
    @Query("SELECT * FROM video")
    LiveData<List<Video>> getAllVideosId();

    @Insert()
    void insert(Video... videos);

    @Query("DELETE FROM video")
    void deleteAll();
}
