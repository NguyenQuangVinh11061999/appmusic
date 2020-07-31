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

public class AdapterBaiHatYeuThich extends RecyclerView.Adapter<AdapterBaiHatYeuThich.Viewholider>  {

    Context context;
    ArrayList<BaiHat> arrayList;

    public AdapterBaiHatYeuThich(Context context, ArrayList<BaiHat> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_baihat_yeuthich, parent, false);
        return new Viewholider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        BaiHat baiHat = arrayList.get(position);
        Glide.with(context)
                .load(baiHat.getHinhBaiHat())
                .into(holder.imgHinhBaiHat);
        holder.txtTenCaSi.setText(baiHat.getCaSi());
        holder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholider extends RecyclerView.ViewHolder {
        ImageView imgHinhBaiHat;
        TextView txtTenBaiHat,txtTenCaSi;
        public Viewholider(@NonNull View itemView) {
            super(itemView);
            imgHinhBaiHat = itemView.findViewById(R.id.imgHinhBaiHatYeuThich);
            txtTenBaiHat = itemView.findViewById(R.id.txtTenbaihatyeuthich);
            txtTenCaSi = itemView.findViewById(R.id.txtTencasiyeuthich);
        }
    }
}
