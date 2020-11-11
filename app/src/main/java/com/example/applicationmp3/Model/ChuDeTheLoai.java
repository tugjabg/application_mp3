package com.example.applicationmp3.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDeTheLoai {

@SerializedName("theLoai")
@Expose
private List<TheLoai> theLoai = null;
@SerializedName("chuDe")
@Expose
private List<ChuDe> chuDe = null;

public List<TheLoai> getTheLoai() {
return theLoai;
}

public void setTheLoai(List<TheLoai> theLoai) {
this.theLoai = theLoai;
}

public List<ChuDe> getChuDe() {
return chuDe;
}

public void setChuDe(List<ChuDe> chuDe) {
this.chuDe = chuDe;
}

}