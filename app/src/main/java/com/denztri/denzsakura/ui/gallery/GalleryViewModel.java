package com.denztri.denzsakura.ui.gallery;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denztri.denzsakura.apicall.GalleryApi;
import com.denztri.denzsakura.apicall.ServiceGenerator;

import java.io.IOException;
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
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class GalleryViewModel extends AndroidViewModel {
    private MutableLiveData<List<GalleryList>> galleryLists;
    private final GalleryRepo galleryRepo;
    private final Executor executor = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        galleryRepo = new GalleryRepo(application);
    }

    private void loadGalleryDb() {
        executor.execute(() -> {
            List<GalleryList> gall = galleryRepo.getAllGall();
            handler.post(() -> galleryLists.setValue(gall));
        });
    }

    public LiveData<List<GalleryList>> getList() throws IOException {
        if (galleryLists == null) galleryLists = new MutableLiveData<>();
        executor.execute(() -> {
            if (galleryRepo.checkDb() != 0) {
                loadGalleryDb();
            } else {
                loadGalleryList();
            }
        });

        return galleryLists;
    }

    public void loadGalleryList() {
        GalleryApi service = ServiceGenerator.createService(GalleryApi.class);
        service.listGallery().enqueue(new Callback<List<GalleryList>>() {
            @Override
            public void onResponse(@NonNull Call<List<GalleryList>> call, @NonNull Response<List<GalleryList>> response) {
                galleryLists.setValue(response.body());
                Executors.newSingleThreadExecutor().execute(() -> galleryRepo.insertGall(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<GalleryList>> call, @NonNull Throwable t) {

            }
        });
    }

    public void deleteGallery(){
        galleryRepo.deleteAllGall();
    }
}