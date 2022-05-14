package com.denztri.denzsakura.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.denztri.denzsakura.ui.media.tab.music.MusicList;

import java.util.List;

@Dao
public interface MusicDao {

    @Query("SELECT * FROM musiclist")
    List<MusicList> getAllMusic();

    @Insert
    void insert(List<MusicList> lists);

    @Query("SELECT COUNT(id) FROM gallerylist")
    int checkDb();

    @Query("DELETE FROM musiclist")
    void deleteAll();
}
