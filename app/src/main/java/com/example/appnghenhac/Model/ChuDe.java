package com.example.appnghenhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDe {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("TenChuDe")
    @Expose
    private String tenChuDe;
    @SerializedName("HinhChuDe")
    @Expose
    private String hinhChuDe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(String hinhChuDe) {
        this.hinhChuDe = hinhChuDe;
    }

}
