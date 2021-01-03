package com.example.applicationmp3.Service;

import com.example.applicationmp3.Model.Album;
import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.Model.ChuDe;
import com.example.applicationmp3.Model.ChuDeTheLoai;
import com.example.applicationmp3.Model.PlayList;
import com.example.applicationmp3.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlayListCurrentDay();
    @GET("chudeTheLoai.php")
    Call<ChuDeTheLoai> GetChuDeTheLoai();
    @GET("albumHot.php")
    Call<List<Album>> getAlbumHot();
    @GET("baihatduocthich.php")
    Call<List<BaiHat>> getBaiHatDuocUaThich();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatBanner(@Field("idquangcao") String idquangcao);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);
    @GET("tatcachude.php")
    Call<List<ChuDe>> getTatCaChuDe();
    @GET("tatcaalbum.php")
    Call<List<Album>> getTatCaAlbum();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getBaiHatTheoAlbum(@Field("idAlbum") String idAlbum);
    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tukhoa);
}

