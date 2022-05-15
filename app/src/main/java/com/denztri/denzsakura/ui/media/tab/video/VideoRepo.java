package com.denztri.denzsakura.ui.media.tab.video;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.Video;
import com.denztri.denzsakura.db.VideoDao;
import com.denztri.denzsakura.ui.media.tab.music.MusicList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VideoRepo {
    public VideoDao videoDao;
    public List<Video> videoList;
    private final Executor executors = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public VideoRepo(Application application) {
        executors.execute(() ->{
            AppDatabase db = AppDatabase.getDbInstance(application);
            videoDao = db.videoDao();
            videoList = videoDao.getAllVideosId();
        });
    }

    public void insertVideos(List<Video> videoList){
        executors.execute(() -> videoDao.insert(videoList));
    }

    public void deleteAllVideos(){
        videoDao.deleteAll();
    }

    public List<Video> getVideoList(){
        return videoDao.getAllVideosId();
    }

}
