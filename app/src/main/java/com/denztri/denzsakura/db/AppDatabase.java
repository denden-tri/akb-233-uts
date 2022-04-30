package com.denztri.denzsakura.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Friend.class, Activity.class}, version = 3,
        autoMigrations = {@AutoMigration(from = 2, to = 3)})
public abstract class AppDatabase extends RoomDatabase {
    public abstract FriendDao friendDao();
    public abstract ActivityDao activityDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "denzsakura")
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {
                                getDbInstance(context).friendDao().insert(Friend.populateData());
                                getDbInstance(context).activityDao().insert(Activity.populateData());
                            });

                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
