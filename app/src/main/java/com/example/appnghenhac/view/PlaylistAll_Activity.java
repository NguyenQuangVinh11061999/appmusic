package com.example.appnghenhac.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.PlaylistApi;
import com.example.appnghenhac.Service.TheLoaiQCAPI;
import com.example.appnghenhac.adapter.PlaylistAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PlaylistAll_Activity extends AppCompatActivity {
    ListView lstDS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    PlaylistApi playlistApi;
    PlaylistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_all_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstDS = findViewById(R.id.lstDsPlaylist);

        Retrofit retrofit = ApiClient.Instrance();
        playlistApi = retrofit.create(PlaylistApi.class);
        getdataPlaylist();
    }

    public void getdataPlaylist() {
        compositeDisposable.add(playlistApi.getdataAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Playlist>>() {
                    @Override
                    public void accept(List<Playlist> posts) throws Exception {
                        ArrayList<Playlist> arrayList = (ArrayList<Playlist>) posts;
                        adapter = new PlaylistAdapter(PlaylistAll_Activity.this,arrayList);
                        lstDS.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }));
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