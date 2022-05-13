package com.denztri.denzsakura.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 12-05-2022
 **/

public class Gallery {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "url")
    public String url;

    public Gallery(String url) {
        this.url = url;
    }
}
