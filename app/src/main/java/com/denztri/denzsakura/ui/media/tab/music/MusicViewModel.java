package com.denztri.denzsakura.ui.media.tab.music;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.denzsakura.apicall.MusicApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicViewModel extends ViewModel {
    private MutableLiveData<List<MusicList>> musicLists;

    public LiveData<List<MusicList>> getList(){
        if (musicLists == null){
            musicLists = new MutableLiveData<>();
            loadMusicList();
        }

        return musicLists;
    }

    public void loadMusicList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-akb-uts-cloud-functions.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MusicApi service = retrofit.create(MusicApi.class);
        service.listMusic().enqueue(new Callback<List<MusicList>>() {
            @Override
            public void onResponse(@NonNull Call<List<MusicList>> call, @NonNull Response<List<MusicList>> response) {
                musicLists.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<MusicList>> call, @NonNull Throwable t) {

            }
        });
    }
}