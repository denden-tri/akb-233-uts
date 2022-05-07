package com.denztri.denzsakura.apicall;

import com.denztri.denzsakura.ui.media.tab.music.MusicList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * NIM                  : 10119233
 * NAMA                 : Denden Triana
 * Kelas                : IF-6
 * Tanggal Pengerjaan   : 30-04-2022
 **/

public interface MusicApi {
    @GET("spotifyGetTopSong2021")
    Call<List<MusicList>> listMusic();
}
