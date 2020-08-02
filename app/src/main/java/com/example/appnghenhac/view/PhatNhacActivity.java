package com.example.appnghenhac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.adapter.ViewPagerAdapter;

public class PhatNhacActivity extends AppCompatActivity {
    ViewPager viewPager;
    SeekBar seekBar;
    TextView txtstart , txtend;
    ImageButton imgTron, imgback, imgplay, imgnext, imgLap;
    public static ViewPagerAdapter viewPagerAdapter;
    fragment_dianhac fragment_dianhac;
    fragment_dsbaihat fragment_dsbaihat;
    BaiHat baiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phat_nhac);
        mapping();

        Intent intent = getIntent();
        if (intent !=null)
            baiHat = (BaiHat) intent.getSerializableExtra("baihat");

        fragment_dianhac = new fragment_dianhac();

        fragment_dsbaihat = new fragment_dsbaihat();
        Bundle bundle = new Bundle();
        bundle.putSerializable("baihat",baiHat);
        fragment_dsbaihat.setArguments(bundle);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.Addfragment(fragment_dianhac);
        viewPagerAdapter.Addfragment(fragment_dsbaihat);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void mapping() {
        viewPager = findViewById(R.id.viewpagerNhac);
        seekBar = findViewById(R.id.seebar);
        txtstart = findViewById(R.id.txt_giostart);
        txtend  = findViewById(R.id.txt_gioiend);
        imgTron = findViewById(R.id.imgTron);
        imgback = findViewById(R.id.imgback);
        imgplay = findViewById(R.id.imgplay);
        imgnext = findViewById(R.id.imgnext);
        imgLap = findViewById(R.id.imgLap);
    }
}