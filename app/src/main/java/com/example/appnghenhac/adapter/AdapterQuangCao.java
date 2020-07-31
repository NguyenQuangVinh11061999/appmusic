package com.example.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.appnghenhac.DanhSachNhac_Activity;
import com.example.appnghenhac.Model.QuangCao;
import com.example.appnghenhac.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterQuangCao extends PagerAdapter {

    Context context;
    List<QuangCao> list;

    public AdapterQuangCao(Context context, List<QuangCao> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_banner,null);

        ImageView imghinh, imgCaKhuc;
        TextView txtTitle, txtChuDe;
        imghinh = view.findViewById(R.id.imgHinhAnh);
        imgCaKhuc = view.findViewById(R.id.imgHinhCaKhuc);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtChuDe = view.findViewById(R.id.txtChuDe);

        final QuangCao quangCao = list.get(position);

        Glide.with(context)
                .load(quangCao.getHinhAnh())
                .into(imghinh);

        Glide.with(context)
                .load(quangCao.getHinhBaiHat())
                .into(imgCaKhuc);

        txtTitle.setText(quangCao.getTenBaiHat());
        txtChuDe.setText(quangCao.getNoiDung());

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachNhac_Activity.class);
                intent.putExtra("banner",list.get(position));
            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
