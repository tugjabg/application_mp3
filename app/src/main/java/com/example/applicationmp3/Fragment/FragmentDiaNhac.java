package com.example.applicationmp3.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applicationmp3.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentDiaNhac extends Fragment {
    CircleImageView circleImageView;
    View view;
    ObjectAnimator objectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container, false);
        circleImageView =view.findViewById(R.id.circleImageViewDiaNhac);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f); // xoay ảnh trong fragment từ 0->360 độ
        objectAnimator.setDuration(10000); // thời gian xoay 10s
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE); // lặp vô hạn
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);// bắt đầu
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
    public void playNhac(String hinhAnh){
        Picasso.with(getContext()).load(hinhAnh).into(circleImageView);
    }
}
