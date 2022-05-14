package com.denztri.denzsakura.ui.media.tab.music;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.MusicDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MusicRepo {
    public MusicDao musicDao;
    public List<MusicList> allMusic;
    private final Executor executors = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public MusicRepo(Application application){
        executors.execute(() ->{
            AppDatabase db = AppDatabase.getDbInstance(application);
            musicDao = db.musicDao();
            allMusic = musicDao.getAllMusic();
        });
    }

    public void insertMusic(List<MusicList> musicLists){
        executors.execute(() -> musicDao.insert(musicLists));
    }

    public void deleteAllMusic(){
        musicDao.deleteAll();
    }

    public List<MusicList> getAllMusic(){
        return musicDao.getAllMusic();
    }

    public int checkDb(){
        return musicDao.checkDb();
    }
}
