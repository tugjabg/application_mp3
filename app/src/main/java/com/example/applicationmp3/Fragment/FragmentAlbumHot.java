package com.example.applicationmp3.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationmp3.Adapter.AlbumHotAdapter;
import com.example.applicationmp3.Model.Album;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAlbumHot extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView txtSeeMore;
    AlbumHotAdapter albumHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerView = (view).findViewById(R.id.RecyclerViewAlbum);
        txtSeeMore = view.findViewById(R.id.xemThem);
        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callBack = dataService.getAlbumHot();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albumList = response.body();
                albumHotAdapter = new AlbumHotAdapter(getActivity(), albumList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(albumHotAdapter);
            }
            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
