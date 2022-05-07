package com.denztri.denzsakura.apicall;

import com.denztri.denzsakura.ui.gallery.GalleryList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 27-04-2022
 **/

public interface GalleryApi {
    @GET("catApiCall")
    Call<List<GalleryList>> listGallery();
}
