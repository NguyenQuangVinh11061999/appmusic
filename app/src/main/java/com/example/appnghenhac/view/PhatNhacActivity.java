package com.example.appnghenhac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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

import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.adapter.ViewPagerAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PhatNhacActivity extends AppCompatActivity {
    ViewPager viewPager;
    SeekBar seekBar;
    TextView txtstart, txtend;
    ImageButton imgTron, imgback, imgplay, imgnext, imgLap;
    public static ViewPagerAdapter viewPagerAdapter;
    fragment_dianhac Fragment_dianhac;
    fragment_dsbaihat Fragment_dsbaihat;
    BaiHat baiHat;
    MediaPlayer mediaPlayer;

    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phat_nhac);
        mapping();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        if (intent != null)
            baiHat = (BaiHat) intent.getSerializableExtra("baihat");

        Fragment_dianhac = new fragment_dianhac();

        Fragment_dsbaihat = new fragment_dsbaihat();
        Bundle bundle = new Bundle();
        bundle.putSerializable("baihat", baiHat);
        Fragment_dsbaihat.setArguments(bundle);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.Addfragment(Fragment_dianhac);
        viewPagerAdapter.Addfragment(Fragment_dsbaihat);
        viewPager.setAdapter(viewPagerAdapter);

        getSupportActionBar().setTitle(baiHat.getTenBaiHat());
        new PlayNhac().execute(baiHat.getLinkBaiHat());
        imgplay.setImageResource(R.drawable.start);
        eventClick();

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

    public void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtstart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                                next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        },300);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){

                }else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);

    }

    private void playClick() {
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.start);
                }
            }
        });
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerAdapter.getItem(1) != null){
                    Fragment_dianhac.PlayHinh(baiHat.getHinhBaiHat());
                    handler.removeCallbacks(this);
                }else{
                    handler.postDelayed(this,300);
                }

            }
        },5000);
        playClick();
    }


    private void mapping() {
        viewPager = findViewById(R.id.viewpagerNhac);
        seekBar = findViewById(R.id.seebar);
        txtstart = findViewById(R.id.txt_giostart);
        txtend = findViewById(R.id.txt_gioiend);
        imgTron = findViewById(R.id.imgTron);
        imgback = findViewById(R.id.imgback);
        imgplay = findViewById(R.id.imgplay);
        imgnext = findViewById(R.id.imgnext);
        imgLap = findViewById(R.id.imgLap);
    }

    public class PlayNhac extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            updateTime();

        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtend.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
}