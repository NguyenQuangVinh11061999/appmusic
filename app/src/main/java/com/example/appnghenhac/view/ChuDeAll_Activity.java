package com.example.appnghenhac.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.ChuDeApi;
import com.example.appnghenhac.adapter.ChuDeAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ChuDeAll_Activity extends AppCompatActivity {

    ChuDeApi chuDeApi;
    RecyclerView recyclerView;
    ChuDeAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de_all_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycleView_ChuDe_All);
        Retrofit retrofit = ApiClient.Instrance();
        chuDeApi = retrofit.create(ChuDeApi.class);

        recyclerView.setHasFixedSize(true);
        // Dang listView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getdataQuangCao();
    }

    public void getdataQuangCao() {
        compositeDisposable.add(chuDeApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChuDe>>() {
                    @Override
                    public void accept(List<ChuDe> posts) throws Exception {
                        ArrayList<ChuDe> arrayList = (ArrayList<ChuDe>) posts;
                        adapter = new ChuDeAdapter(ChuDeAll_Activity.this,arrayList);
                        Log.i("aab",arrayList.get(0).getTenChuDe());
                        recyclerView.setAdapter(adapter);
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
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