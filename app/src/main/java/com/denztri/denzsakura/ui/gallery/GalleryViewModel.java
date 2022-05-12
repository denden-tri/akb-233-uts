package com.denztri.denzsakura.ui.gallery;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.denzsakura.apicall.GalleryApi;
import com.denztri.denzsakura.apicall.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public class GalleryViewModel extends ViewModel {
    private MutableLiveData<List<GalleryList>> galleryLists;

    public LiveData<List<GalleryList>> getList() throws IOException {
        if (galleryLists == null){
            galleryLists = new MutableLiveData<>();
            loadGalleryList();
        }

        return galleryLists;
    }

    public void loadGalleryList() {
        GalleryApi service = ServiceGenerator.createService(GalleryApi.class);
        service.listGallery().enqueue(new Callback<List<GalleryList>>() {
            @Override
            public void onResponse(@NonNull Call<List<GalleryList>> call, @NonNull Response<List<GalleryList>> response) {
                galleryLists.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<GalleryList>> call, @NonNull Throwable t) {

            }
        });
    }
}