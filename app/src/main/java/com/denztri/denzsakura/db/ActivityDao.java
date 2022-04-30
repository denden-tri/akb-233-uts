package com.denztri.denzsakura.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM activity")
    List<Activity> getAllActivities();

    @Insert
    void insert(Activity... Activities);

    @Query("DELETE FROM activity")
    void deleteAll();
}
