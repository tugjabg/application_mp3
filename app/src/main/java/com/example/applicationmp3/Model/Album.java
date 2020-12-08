package com.example.applicationmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("tenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("tenCaSiAlbum")
    @Expose
    private String tenCaSiAlbum;
    @SerializedName("hinhAlbum")
    @Expose
    private String hinhAlbum;

    public String getIdAlbum() {
    return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
    this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
    return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
    this.tenAlbum = tenAlbum;
    }

    public String getTenCaSiAlbum() {
    return tenCaSiAlbum;
    }

    public void setTenCaSiAlbum(String tenCaSiAlbum) {
    this.tenCaSiAlbum = tenCaSiAlbum;
    }

    public String getHinhAlbum() {
    return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
    this.hinhAlbum = hinhAlbum;
    }

}