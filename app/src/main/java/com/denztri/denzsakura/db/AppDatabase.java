package com.denztri.denzsakura.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Friend.class, Activity.class, Video.class}, version = 4,
        autoMigrations = {@AutoMigration(from = 3, to = 4)})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao friendDao();
    public abstract ActivityDao activityDao();
    public abstract VideoDao    videoDao();

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
}
