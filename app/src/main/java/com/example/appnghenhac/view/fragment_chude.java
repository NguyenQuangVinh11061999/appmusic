package com.example.appnghenhac.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class fragment_chude extends Fragment {
    View view;
    ChuDeApi chuDeApi;
    RecyclerView recyclerView;
    ChuDeAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView txtXemThem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude,container,false);
        recyclerView = view.findViewById(R.id.recycleView);
        Retrofit retrofit = ApiClient.Instrance();
        chuDeApi = retrofit.create(ChuDeApi.class);
        txtXemThem = view.findViewById(R.id.txtXemThem_Chude);

        recyclerView.setHasFixedSize(true);
        // Dang listView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getdataQuangCao();


        txtXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChuDeAll_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getdataQuangCao() {
        compositeDisposable.add(chuDeApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChuDe>>() {
                    @Override
                    public void accept(List<ChuDe> posts) throws Exception {
                       ArrayList<ChuDe> arrayList = (ArrayList<ChuDe>) posts;
                       adapter = new ChuDeAdapter(getContext(),arrayList);
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
}
