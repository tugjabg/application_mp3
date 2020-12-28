package com.example.applicationmp3.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDe {

@SerializedName("IdKeyChuDe")
@Expose
private String idKeyChuDe;
@SerializedName("TenChuDe")
@Expose
private String tenChuDe;
@SerializedName("HinhAnhChuDe")
@Expose
private String hinhAnhChuDe;

public String getIdKeyChuDe() {
return idKeyChuDe;
}

public void setIdKeyChuDe(String idKeyChuDe) {
this.idKeyChuDe = idKeyChuDe;
}

public String getTenChuDe() {
return tenChuDe;
}

public void setTenChuDe(String tenChuDe) {
this.tenChuDe = tenChuDe;
}

public String getHinhAnhChuDe() {
return hinhAnhChuDe;
}

public void setHinhAnhChuDe(String hinhAnhChuDe) {
this.hinhAnhChuDe = hinhAnhChuDe;
}

    @Override
    public String toString() {
        return "ChuDe{" +
                "idKeyChuDe='" + idKeyChuDe + '\'' +
                ", tenChuDe='" + tenChuDe + '\'' +
                ", hinhAnhChuDe='" + hinhAnhChuDe + '\'' +
                '}';
    }
}