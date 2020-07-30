package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.TheLoai;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TheLoaiApi {
    @GET("getdataTheLoai.php")
    Observable<List<TheLoai>> getdata();
}
