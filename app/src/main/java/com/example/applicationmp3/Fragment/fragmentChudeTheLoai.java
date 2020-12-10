package com.example.applicationmp3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.applicationmp3.Activity.DanhSachBaiHatActivity;
import com.example.applicationmp3.Activity.DanhSachTatCaChuDeActivity;
import com.example.applicationmp3.Model.ChuDe;
import com.example.applicationmp3.Model.ChuDeTheLoai;
import com.example.applicationmp3.Model.TheLoai;
import com.example.applicationmp3.R;
import com.example.applicationmp3.Service.APIService;
import com.example.applicationmp3.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragmentChudeTheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView xemThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // tạo một cái view chứa cái fragment_chude_theloai
        view = inflater.inflate(R.layout.fragment_chude_theloai, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView); // ánh xạ horizontalScrollView
        xemThem = view.findViewById(R.id.xemThem); // ánh xạ cái view xem thêm
        xemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaChuDeActivity.class);
                startActivity(intent);
            }
        });
        GetData(); // lấy dữ liệu từ API
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService(); // link đến đường dẫn https://projecti20201.000webhostapp.com/Server/
        Call<ChuDeTheLoai> callback = dataService.GetChuDeTheLoai(); // lấy API từ Server/chudeTheLoai.php dưới dạng JSON
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeTheLoai = response.body(); // decode lại data dưới dạng JSON về dạng Object ChuDeTheLoai
                final List<ChuDe> chuDeList = new ArrayList<>();
                chuDeList.addAll(chuDeTheLoai.getChuDe());

                final List<TheLoai> theLoaiList = new ArrayList<>();
                theLoaiList.addAll(chuDeTheLoai.getTheLoai());
                LinearLayout linearLayout = new LinearLayout(getActivity()); // truyền màn hình sử dụng cái viewgroup, lấy màn hình từ activity từ màn hình này đưa vào
                linearLayout.setOrientation(LinearLayout.HORIZONTAL); // vẽ theo chiều ngang
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580,250); // xét kích thước cho linearLayout
                layoutParams.setMargins(10,20,10,30);
                for(int i = 0; i < chuDeList.size(); i++){
                    CardView cardView = new CardView(getActivity()); // lấy cardView bo xung quanh 1 horizontalScrollView
                    cardView.setRadius(10); // set đường viền cho cardView
                    ImageView imageView = new ImageView(getActivity()); // tạo một ImageView ngay trong màn hình của Resource cần hiển thị
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY); // tự căn chỉnh chiều x,y cho phù hợp với ảnh
                    if(chuDeList.get(i) != null){
                        Picasso.with(getActivity()).load(chuDeList.get(i).getHinhAnhChuDe()).into(imageView); // nén hình ảnh dưới dạng url vào trong một imageView
                    }
                    cardView.setLayoutParams(layoutParams); // set chiều rộng, cao cho cardview
                    cardView.addView(imageView); // đưa hình ảnh vừa lấy được từ các vị trí của biến i vào trong cardview
                    linearLayout.addView(cardView); // đưa cardview vào trong linearLayout
                }
                for(int i = 0; i < theLoaiList.size(); i++){
                    CardView cardView = new CardView(getActivity()); // lấy cardView bo xung quanh 1 horizontalScrollView
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiList.get(i) != null){
                        Picasso.with(getActivity()).load(theLoaiList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);  // đưa cardview vào trong linearLayout
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai", theLoaiList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
}
