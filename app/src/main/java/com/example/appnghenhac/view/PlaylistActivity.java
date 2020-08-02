package com.example.appnghenhac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.TheLoaiApi;
import com.example.appnghenhac.Service.TheLoaiQCAPI;
import com.example.appnghenhac.adapter.BaiHatPlaylistAdapter;
import com.example.appnghenhac.adapter.TheLoaiAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PlaylistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TheLoaiQCAPI theLoaiQCAPI;
    Playlist playlist;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        mapping();
        getIntentDL();

        Retrofit retrofit = ApiClient.Instrance();
        theLoaiQCAPI = retrofit.create(TheLoaiQCAPI.class);

        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        getdatatheLoai(playlist.getId());
    }

    private void mapping() {
        recyclerView = findViewById(R.id.recycleDSPlaylist);
    }

    private void getIntentDL() {
        Intent intent = getIntent();
        if (intent != null)
           playlist = (Playlist) intent.getSerializableExtra("playlist");
    }
    public void getdatatheLoai(int id) {
        compositeDisposable.add(theLoaiQCAPI.getdata(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaiHat>>() {
                    @Override
                    public void accept(List<BaiHat> posts) throws Exception {
                       ArrayList<BaiHat> arrayList = (ArrayList<BaiHat>) posts;
                        BaiHatPlaylistAdapter adapter = new BaiHatPlaylistAdapter(PlaylistActivity.this,arrayList);
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