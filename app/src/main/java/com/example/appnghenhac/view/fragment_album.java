package com.example.appnghenhac.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.AlbumApi;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.adapter.AdapterAlbum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class fragment_album extends Fragment {
    public  AlbumApi albumApi;
    public  View view;
    public  CompositeDisposable compositeDisposable = new CompositeDisposable();
     AdapterAlbum adapterAlbum;
    public  RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);

        recyclerView = view.findViewById(R.id.recycleViewAlbum);


        Retrofit retrofit = ApiClient.Instrance();
        albumApi = retrofit.create(AlbumApi.class);
        recyclerView.setHasFixedSize(true);
        // Dang listView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getdataAlbum();
        return view;
    }

    public void getdataAlbum() {
        compositeDisposable.add(albumApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Album>>() {
                    @Override
                    public void accept(List<Album> posts) throws Exception {
                        ArrayList<Album> arrayList = (ArrayList<Album>) posts;
                        adapterAlbum = new AdapterAlbum(getActivity(),arrayList);
                        recyclerView.setAdapter(adapterAlbum);
                        adapterAlbum.notifyDataSetChanged();
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }



}
