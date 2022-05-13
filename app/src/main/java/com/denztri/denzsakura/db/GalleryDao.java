package com.denztri.denzsakura.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.migration.AutoMigrationSpec;

import com.denztri.denzsakura.ui.gallery.GalleryList;

import java.util.List;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 12-05-2022
 **/

@Dao
public interface GalleryDao extends AutoMigrationSpec {

    @Query("SELECT * FROM gallerylist")
    List<GalleryList> getAllGallery();

    @Insert
    void insert(List<GalleryList> lists);

    @Query("SELECT COUNT(id) FROM gallerylist")
    int checkDb();

    @Query("DELETE FROM gallerylist")
    void deleteAll();
}
