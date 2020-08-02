package com.example.appnghenhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class baiHatAdapter extends RecyclerView.Adapter<baiHatAdapter.Viewholider> {

    Context context;
    ArrayList<BaiHat> arrayList;

    public baiHatAdapter(Context context, ArrayList<BaiHat> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_baihat, parent, false);
        return new Viewholider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        BaiHat baiHat = arrayList.get(position);
        Glide.with(context)
                .load(baiHat.getHinhBaiHat())
                .into(holder.imghinh);
        holder.txttencasi.setText(baiHat.getCaSi());
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholider extends RecyclerView.ViewHolder {
        ImageView imghinh;
        TextView txttenbaihat, txttencasi;

        public Viewholider(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.img_hinhbh);
            txttenbaihat = itemView.findViewById(R.id.txtten_baihat);
            txttencasi = itemView.findViewById(R.id.txt_tencasi);
        }
    }
}
