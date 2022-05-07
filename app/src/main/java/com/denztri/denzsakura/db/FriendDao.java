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
 * Tanggal Pengerjaan   : 29-04-2022
 **/

@Dao
public interface FriendDao {
    @Query("SELECT * FROM friend")
    LiveData<List<Friend>> getAllFriends();

    @Insert
    void insert(Friend... friends);

    @Query("DELETE FROM friend")
    void deleteAll();
}
