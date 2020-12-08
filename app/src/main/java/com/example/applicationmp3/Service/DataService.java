package com.example.applicationmp3.Service;

import com.example.applicationmp3.Model.Album;
import com.example.applicationmp3.Model.ChuDeTheLoai;
import com.example.applicationmp3.Model.PlayList;
import com.example.applicationmp3.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService { // lấy data từ service
    @GET("songbanner.php") // lấy json về bằng phương thức get
    Call<List<Quangcao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlayListCurrentDay(); // getPlayListCurrent giống như tên function để nhận dữ liệu
    @GET("chudeTheLoai.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();
    @GET("albumHot.php")
    Call<List<Album>> getAlbumHot();
}
