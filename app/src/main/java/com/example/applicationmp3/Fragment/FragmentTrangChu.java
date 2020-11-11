package com.example.applicationmp3.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.applicationmp3.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment; // custom viewpager

public class FragmentTrangChu extends Fragment {
    View view;
    @Nullable
    @Override // gan view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttrangchu, container, false);
        return view;
    }
}
