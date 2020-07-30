package com.example.appnghenhac.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.ApiClient;
import com.example.appnghenhac.Service.BannerApi;
import com.example.appnghenhac.adapter.AdapterQuangCao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Retrofit;

public class fragment_banner extends Fragment {
    View view;
    BannerApi bannerApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    Runnable runnable;
    Handler handler;

    SwipeRefreshLayout swipeRefreshLayout;
    AdapterQuangCao adapter;
    int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        Retrofit retrofit = ApiClient.Instrance();
        bannerApi = retrofit.create(BannerApi.class);
        getdataQuangCao();
        

        return view;
    }

    public void mapping() {
        viewPager = view.findViewById(R.id.viewflipper);
        circleIndicator = view.findViewById(R.id.circle);
      //   swipeRefreshLayout = view.findViewById(R.id.swiprefreshlayout);
    }



    public void getdataQuangCao() {
        compositeDisposable.add(bannerApi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuangCao>>() {
                    @Override
                    public void accept(List<QuangCao> posts) throws Exception {
                        ArrayList<QuangCao> arrayList = (ArrayList<QuangCao>) posts;
                        Log.i("aaa", "" + arrayList.get(0).getTenBaiHat());
                        adapter = new AdapterQuangCao(getContext(), arrayList);
                        viewPager.setAdapter(adapter);
                        circleIndicator.setViewPager(viewPager);
                        handler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                index = viewPager.getCurrentItem();
                                index++;
                                if (index > viewPager.getAdapter().getCount()) {
                                    index = 0;
                                }

                                viewPager.setCurrentItem(index, true);
                                handler.postDelayed(runnable, 4500);
                            }
                        };
                        handler.postDelayed(runnable, 4500);
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
