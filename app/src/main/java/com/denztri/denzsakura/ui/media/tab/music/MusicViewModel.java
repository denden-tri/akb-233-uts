package com.denztri.denzsakura.ui.media.tab.music;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denztri.denzsakura.apicall.MusicApi;
import com.denztri.denzsakura.apicall.ServiceGenerator;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public class MusicViewModel extends AndroidViewModel {
    private MutableLiveData<List<MusicList>> musicLists;
    private final MusicRepo musicRepo;
    private final Executor executor = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public MusicViewModel(@NonNull Application application) {
        super(application);
        musicRepo = new MusicRepo((application));
    }


    public LiveData<List<MusicList>> getList(){
        if (musicLists == null) musicLists = new MutableLiveData<>();
        executor.execute(() -> {
            if (musicRepo.checkDb() != 0){
                loadMusicDb();
            } else {
                loadMusicList();
            }
        });
        return musicLists;
    }

    private void loadMusicDb() {
        executor.execute(() -> {
            List<MusicList> musll = musicRepo.getAllMusic();
            handler.post(() -> musicLists.setValue(musll));
        });
    }

    public void loadMusicList() {
        MusicApi service = ServiceGenerator.createService(MusicApi.class);
        service.listMusic().enqueue(new Callback<List<MusicList>>() {
            @Override
            public void onResponse(@NonNull Call<List<MusicList>> call, @NonNull Response<List<MusicList>> response) {
                musicLists.setValue(response.body());
                executor.execute(() -> musicRepo.insertMusic(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<MusicList>> call, @NonNull Throwable t) {

            }
        });
    }

    public void deleteMusic(){
        musicRepo.deleteAllMusic();
    }
}