package com.example.appnghenhac.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class fragment_dsbaihat extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<BaiHat> arrayList = new ArrayList<>();
    BaiHat baiHat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danhsachbaihat, container, false);
        recyclerView = view.findViewById(R.id.recycleView_BaiHat);
        Bundle bundle = getArguments();
        if (bundle != null)
            baiHat = (BaiHat) bundle.getSerializable("baihat");

        arrayList.add(baiHat);

        return view;

    }
}
