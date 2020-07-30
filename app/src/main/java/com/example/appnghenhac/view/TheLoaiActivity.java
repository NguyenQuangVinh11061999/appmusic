package com.example.appnghenhac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.ChuDeApi;
import com.example.appnghenhac.Service.TheLoaiApi;
import com.example.appnghenhac.adapter.PlaylistAdapter;
import com.example.appnghenhac.adapter.TheLoaiAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TheLoaiActivity extends AppCompatActivity {

    TheLoaiApi theloaiApi;
    RecyclerView recyclerView;
    TheLoaiAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        recyclerView = findViewById(R.id.recycleViewTheLoai);
        Retrofit retrofit = ApiClient.Instrance();
        theloaiApi = retrofit.create(TheLoaiApi.class);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        getdatatheLoai();

    }

    public void getdatatheLoai() {
        compositeDisposable.add(theloaiApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TheLoai>>() {
                    @Override
                    public void accept(List<TheLoai> posts) throws Exception {

                        ArrayList<TheLoai> arrayList = (ArrayList<TheLoai>) posts;
                        Toast.makeText(TheLoaiActivity.this, "" + posts.get(2).getIdTheLoai(), Toast.LENGTH_SHORT).show();

                        ArrayList<TheLoai> theLoais = new ArrayList<>();
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).getIdChuDe() == id) {
                                theLoais.add(arrayList.get(i));
                            }
                        }
                        adapter = new TheLoaiAdapter(TheLoaiActivity.this, theLoais);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}