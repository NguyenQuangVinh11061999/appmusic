package com.example.appnghenhac.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.AlbumApi;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.BaihatyeuthichApi;
import com.example.appnghenhac.adapter.AdapterAlbum;
import com.example.appnghenhac.adapter.AdapterBaiHatYeuThich;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class fragment_baihat_yeuthich extends Fragment {
    BaihatyeuthichApi baihatyeuthichApi;
    View view;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AdapterBaiHatYeuThich adapter;
     RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihatthich,container,false);
        recyclerView = view.findViewById(R.id.recycleViewBaiHatThich);

        Retrofit retrofit = ApiClient.Instrance();
        baihatyeuthichApi = retrofit.create(BaihatyeuthichApi.class);

        recyclerView.setHasFixedSize(true);
        // Dang listView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.HORIZONTAL);
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.custom_recycler);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        getdatabaihat();
        return view;
    }

    public void getdatabaihat() {
        compositeDisposable.add(baihatyeuthichApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaiHat>>() {
                    @Override
                    public void accept(List<BaiHat> posts) throws Exception {
                        ArrayList<BaiHat> arrayList = (ArrayList<BaiHat>) posts;
                        adapter = new AdapterBaiHatYeuThich(getActivity(),arrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
