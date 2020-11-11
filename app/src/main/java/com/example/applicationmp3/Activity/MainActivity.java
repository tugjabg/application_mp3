package com.example.applicationmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.applicationmp3.Adapter.MainViewPagerAdapter;
import com.example.applicationmp3.Fragment.FragmentTimKiem;
import com.example.applicationmp3.Fragment.FragmentTrangChu;
import com.example.applicationmp3.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init(); // đưa các fragment vào trong viewpager
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),1);
        mainViewPagerAdapter.addFragment(new FragmentTrangChu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new FragmentTimKiem(), "Tìm kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
    }

    void mapping(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}