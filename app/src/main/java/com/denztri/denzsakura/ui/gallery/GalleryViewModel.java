package com.denztri.denzsakura.ui.gallery;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.denzsakura.apicall.GalleryApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-akb-uts-cloud-functions.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GalleryApi service = retrofit.create(GalleryApi.class);
//        List<GalleryList> galleryListsRes = service.listGallery().execute().body();
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