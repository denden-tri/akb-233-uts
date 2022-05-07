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
 * Tanggal Pengerjaan   : 25-04-2022
 **/

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM activity")
    LiveData<List<Activity>> getAllActivities();

    @Insert
    void insert(Activity... Activities);

    @Query("DELETE FROM activity")
    void deleteAll();
}
