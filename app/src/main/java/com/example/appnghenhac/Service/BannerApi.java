package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.QuangCao;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BannerApi {
    @GET("getdata.php")
    Observable<List<QuangCao>> getdata();
}
