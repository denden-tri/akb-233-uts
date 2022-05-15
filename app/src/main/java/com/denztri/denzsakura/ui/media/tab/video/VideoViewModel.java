package com.denztri.denzsakura.ui.media.tab.video;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denztri.denzsakura.db.Video;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 01-05-2022
 **/

public class VideoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Video>> videosLists;
    private final VideoRepo videoRepo;
    private final Executor executor = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());


    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoRepo = new VideoRepo(application);
    }

    public LiveData<List<Video>> getList(){
        if (videosLists == null) videosLists = new MutableLiveData<>();

        executor.execute(this::loadVideoList);

        return videosLists;
    }

    private void loadVideoList() {
        executor.execute(() -> {
            List<Video> videos = videoRepo.getVideoList();
            handler.post(() -> videosLists.setValue(videos));
        });
    }

    public void deleteVideo(){
        videoRepo.deleteAllVideos();
    }

    public void repopulateVideo(){
        videoRepo.insertVideos(Arrays.asList(Video.populateData()));
    }
}