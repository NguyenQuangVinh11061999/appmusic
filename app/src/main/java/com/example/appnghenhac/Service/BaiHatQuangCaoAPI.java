package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.BaiHat;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaiHatQuangCaoAPI {
    @FormUrlEncoded
    @POST("getdataBaiHatQuangCao.php")
    Observable<List<BaiHat>> getdata(@Field("idquangcao") int idquangcao);
}
