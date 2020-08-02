package com.example.appnghenhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("Hinhicon")
    @Expose
    private String hinhicon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHinhicon() {
        return hinhicon;
    }

    public void setHinhicon(String hinhicon) {
        this.hinhicon = hinhicon;
    }

}
