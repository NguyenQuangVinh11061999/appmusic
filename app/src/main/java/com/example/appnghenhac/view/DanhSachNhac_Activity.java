package com.example.appnghenhac.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.BaiHatQuangCaoAPI;
import com.example.appnghenhac.Service.BaihatyeuthichApi;
import com.example.appnghenhac.adapter.AdapterAlbum;
import com.example.appnghenhac.adapter.AdapterQuangCaoBaiHat;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DanhSachNhac_Activity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton floatingActionButton;
    BaiHatQuangCaoAPI baiHatQuangCaoAPI;
    QuangCao quangCao;
    TextView txtTenbaihat;
    ImageView imghinhAnh;
    ImageView imghinh;
    RecyclerView recyclerView;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhac_);
        getIntentDL();
        mapping();
        init();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getdataQuangCao(quangCao.getId());
        setValueView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());
    }

    private void setValueView(String ten,String hinhanh) {
        txtTenbaihat.setText(ten);

        Glide.with(this)
                .load(hinhanh)
                .into(imghinh);

        Glide.with(this)
                .load(hinhanh)
                .into(imghinhAnh);
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void getdataQuangCao(int id) {
        Retrofit retrofit = ApiClient.Instrance();
        baiHatQuangCaoAPI = retrofit.create(BaiHatQuangCaoAPI.class);
        compositeDisposable.add(baiHatQuangCaoAPI.getdata(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaiHat>>() {
                    @Override
                    public void accept(List<BaiHat> posts) throws Exception {
                        ArrayList<BaiHat> arrayList = (ArrayList<BaiHat>) posts;
                        AdapterQuangCaoBaiHat adapterQuangCaoBaiHat = new AdapterQuangCaoBaiHat(DanhSachNhac_Activity.this,arrayList);
                        recyclerView.setAdapter(adapterQuangCaoBaiHat);
                        adapterQuangCaoBaiHat.notifyDataSetChanged();
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }


    public void mapping() {
        imghinh = findViewById(R.id.imgHinh);
        imghinhAnh = findViewById(R.id.imghinhdanhsachbaihat);
        txtTenbaihat = findViewById(R.id.txtTenbaihatQuangCao);
        collapsingToolbarLayout = findViewById(R.id.collapsingbar);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        recyclerView = findViewById(R.id.recyclerViewDanhSach);
        floatingActionButton = findViewById(R.id.FloatingActionButton);
        floatingActionButton = findViewById(R.id.FloatingActionButton);
    }

    public void getIntentDL() {
        Intent intent = getIntent();
        if(intent !=null)
              quangCao = (QuangCao) intent.getSerializableExtra("banner");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}