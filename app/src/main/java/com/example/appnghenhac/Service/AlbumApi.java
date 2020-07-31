package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.Album;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AlbumApi {
    @GET("getdataAlbum.php")
    Observable<List<Album>> getdata();
}
