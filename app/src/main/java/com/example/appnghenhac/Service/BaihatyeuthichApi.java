package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.BaiHat;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BaihatyeuthichApi {
    @GET("getdatabaiahatyeuthich.php")
    Observable<List<BaiHat>> getdata();
}
