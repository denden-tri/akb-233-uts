package com.denztri.denzsakura.apicall;

import com.denztri.denzsakura.ui.gallery.GalleryList;
import com.denztri.denzsakura.ui.media.tab.music.MusicList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MusicApi {
    @GET("spotifyGetTopSong2021")
    Call<List<MusicList>> listMusic();
}
