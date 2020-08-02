package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.Playlist;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PlaylistApi {
    @GET("getdataplaylist.php")
    Observable<List<Playlist>> getData();

    @GET("getdataAll_playlist.php")
    Observable<List<Playlist>> getdataAll();
}
