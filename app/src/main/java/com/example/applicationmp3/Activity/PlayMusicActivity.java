package com.example.applicationmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.applicationmp3.Adapter.ViewPagerPlayListNhac;
import com.example.applicationmp3.Fragment.FragmentDiaNhac;
import com.example.applicationmp3.Fragment.FragmentPlayBaiHat;
import com.example.applicationmp3.Model.BaiHat;
import com.example.applicationmp3.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlayMusic;
    TextView txtTimSong, txtTotalSong;
    SeekBar seekBar;
    ImageButton imgPlay, imgRepeat, imgNext, imgPre, imgRandom;
    ViewPager viewPager;
    public static List<BaiHat> mangBaiHat = new ArrayList<>();
    public static ViewPagerPlayListNhac viewPagerPlayListNhac;
    FragmentDiaNhac fragmentDiaNhac;
    FragmentPlayBaiHat fragmentPlayBaiHat;
    MediaPlayer mediaPlayer; // dung de play ca khuc
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataFromIntent();
        mapping();
        evenClick(); // bat su kien click trong man hinh choi nhac
    }

    private void evenClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlayListNhac.getItem(1)!=null){
                    if (mangBaiHat.size() >= 0){
                        fragmentDiaNhac.playNhac(mangBaiHat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this); // xóa hình ảnh trước đi
                    }else {
                        handler.postDelayed(this, 300);
                    }
                };
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void getDataFromIntent() {
        mangBaiHat.clear();
        Intent intent = getIntent();
        if (intent.hasExtra("cakhuc")){
            BaiHat baiHat = (BaiHat) intent.getSerializableExtra("cakhuc");
            mangBaiHat.add(baiHat);
        }
    }

    private void mapping() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayMusic);
        txtTimSong = findViewById(R.id.txtViewTimeSong);
        txtTotalSong = findViewById(R.id.sumTimeSong);
        seekBar = findViewById(R.id.seekBarSong);
        imgPlay = findViewById(R.id.iconplay);
        imgRepeat = findViewById(R.id.iconrepeat);
        imgNext = findViewById(R.id.iconnext);
        imgPre = findViewById(R.id.iconpreview);
        imgRandom = findViewById(R.id.imgButtonSuffe);
        viewPager = findViewById(R.id.viewPagerPlayMusic);
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangBaiHat.clear();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        fragmentPlayBaiHat = new FragmentPlayBaiHat();
        fragmentDiaNhac = new FragmentDiaNhac();
        viewPagerPlayListNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        viewPagerPlayListNhac.addFragment(fragmentDiaNhac);
        viewPagerPlayListNhac.addFragment(fragmentPlayBaiHat);
        viewPager.setAdapter(viewPagerPlayListNhac);
        fragmentDiaNhac = (FragmentDiaNhac) viewPagerPlayListNhac.getItem(0);
        if (mangBaiHat.size() > 0){
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkbaihat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); // nghe dưới dạng online
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // Khi quá lâu không phát được. sẽ reset bài hát
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            try {
                mediaPlayer.setDataSource(s);// khởi tạo đường link từ ca khúc
                mediaPlayer.prepare(); // dùng để phát bài hát
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("mm:ss");
        txtTotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
}