package com.example.appnghenhac.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.BannerApi;
import com.example.appnghenhac.Service.PlaylistApi;
import com.example.appnghenhac.adapter.AdapterQuangCao;
import com.example.appnghenhac.adapter.PlaylistAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class fragment_playlist extends Fragment {
    View view;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    PlaylistApi playlistApi;
    PlaylistAdapter adapter;
    ListView listView;
    TextView txtHienThi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        mapping();
        Retrofit retrofit = ApiClient.Instrance();
        playlistApi = retrofit.create(PlaylistApi.class);

        getdataQuangCao();

        txtHienThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PlaylistAll_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void mapping() {
        listView = view.findViewById(R.id.lstPlayList);
        txtHienThi = view.findViewById(R.id.txtXemThemPlaylist);
    }

    public void getdataQuangCao() {
        compositeDisposable.add(playlistApi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Playlist>>() {
                    @Override
                    public void accept(List<Playlist> posts) throws Exception {
                        ArrayList<Playlist> arrayList = (ArrayList<Playlist>) posts;
                        //Toast.makeText(getActivity(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
                        adapter = new PlaylistAdapter(getActivity(), arrayList);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        setListViewHeightBasedOnChildren(listView);
                    }
                }));
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
