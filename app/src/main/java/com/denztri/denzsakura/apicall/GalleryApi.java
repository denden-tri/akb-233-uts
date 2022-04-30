package com.denztri.denzsakura.apicall;

import com.denztri.denzsakura.ui.gallery.GalleryList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GalleryApi {
    @GET("catApiCall")
    Call<List<GalleryList>> listGallery();
}
