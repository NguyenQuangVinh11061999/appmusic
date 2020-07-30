package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.ChuDe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ChuDeApi {
    @GET("getdatChuDe.php")
    Observable<List<ChuDe>> getdata();
}
