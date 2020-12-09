package com.example.applicationmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.applicationmp3.Adapter.DanhSachBaiHatAdapter;
import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.Model.PlayList;
import com.example.applicationmp3.Model.Quangcao;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    ImageView imgdanhsachcakhuc;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    Quangcao quangcao;
    PlayList playList;
    List<BaiHat> listBaiHat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        DataIntent();
        mapping();
        init();
        if (quangcao != null && quangcao.getTenbaihat().equals("")){
            try {
                setValueInView(quangcao.getTenbaihat(), quangcao.getHinhanh());
            } catch (IOException e) {
                e.printStackTrace();
            }
            getDataQuangCao(quangcao.getIdQuangCao());
        }
        if (playList != null && playList.getTen().equals("")){
            try {
                setValueInView(playList.getTen(), playList.getHinhPlaylist());

            } catch (IOException e) {
                e.printStackTrace();
            }
            getDataPlayList(playList.getIdPlaylist());
        }
    }

    private void getDataPlayList(String idPlayList) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getDanhSachBaiHatTheoPlayList(idPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listBaiHat = response.body();
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getBaiHatBanner(idquangcao);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                listBaiHat = response.body();
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinhanh) throws IOException {
        collapsingToolbarLayout.setTitle(ten);
        URL url = new URL(hinhanh);
        Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        collapsingToolbarLayout.setBackground(bitmapDrawable);
        Picasso.with(this).load(hinhanh).into(imgdanhsachcakhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        coordinatorLayout = findViewById(R.id.coordiLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbar);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recycleViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imgdanhsachcakhuc = findViewById(R.id.imgViewDanhSachCaKhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemPlayList")){
                playList = (PlayList) intent.getSerializableExtra("itemPlayList");
            }
        }
    }
}