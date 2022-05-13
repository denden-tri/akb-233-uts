package com.denztri.denzsakura.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.denztri.denzsakura.ui.gallery.GalleryList;

import java.util.concurrent.Executors;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 25-04-2022
 **/

@Database(entities = {Friend.class, Activity.class, Video.class, GalleryList.class}, version = 6,
        autoMigrations = {@AutoMigration(from = 5, to = 6, spec = AppDatabase.MyAutoMigration.class)})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao   friendDao();
    public abstract ActivityDao activityDao();
    public abstract VideoDao    videoDao();
    public abstract GalleryDao  galleryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "denzsakura")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {
                                getDbInstance(context).friendDao().insert(Friend.populateData());
                                getDbInstance(context).activityDao().insert(Activity.populateData());
                                getDbInstance(context).videoDao().insert(Video.populateData());
                            });

                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    @RenameTable(fromTableName = "Gallery", toTableName = "GalleryList")
    public static class MyAutoMigration implements AutoMigrationSpec{}
}
