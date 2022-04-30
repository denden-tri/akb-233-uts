package com.denztri.denzsakura.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FriendDao {
    @Query("SELECT * FROM friend")
    List<Friend> getAllFriends();

    @Insert
    void insert(Friend... friends);

    @Query("DELETE FROM friend")
    void deleteAll();
}
