package com.example.applicationmp3.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.applicationmp3.Adapter.BannerAdapter;
import com.example.applicationmp3.Model.Quangcao;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragmentBanner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    Runnable runnable;
    Handler handler;
    int curent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        GetData();
        mapping();
        return view;
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicaterDefault);
    }

    private void GetData() {
        DataService dataService = APIService.getService(); // lấy url của service
        Call<List<Quangcao>> callBack = dataService.GetDataBanner();
        callBack.enqueue(new Callback<List<Quangcao>>() { // lắng nghe cho dữ liệu trả về từ API
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) { // có kết quả trả về
                List<Quangcao> quangcaoList = response.body(); // lấy dữ liệu trong kq trả về từ server
                BannerAdapter bannerAdapter;
                System.out.println(quangcaoList.size());
                bannerAdapter = new BannerAdapter(getActivity(), quangcaoList);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        curent = viewPager.getCurrentItem();
                        curent ++;
                        if(curent > viewPager.getAdapter().getCount()){
                            curent = 0;
                        }
                        viewPager.setCurrentItem(curent, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
                // cấu trúc viết sai || không có data trả về || kết nối thất bại
                System.out.println(t.getMessage());
            }
        });

    }
}
