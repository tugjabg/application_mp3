package com.example.applicationmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.applicationmp3.Adapter.TatCaAlbumAdapter;
import com.example.applicationmp3.Model.Album;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbumActivity extends AppCompatActivity {
    List<Album> albumList;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TatCaAlbumAdapter tatCaAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album);
        toolbar = findViewById(R.id.toolbarAllAlbum);
        recyclerView = findViewById(R.id.recyclerTatCaAlbum);
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callBack = dataService.getTatCaAlbum();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumList = response.body();
                tatCaAlbumAdapter = new TatCaAlbumAdapter(DanhSachTatCaAlbumActivity.this, albumList);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbumActivity.this,2));
                recyclerView.setAdapter(tatCaAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các Album");
    }
}