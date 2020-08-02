package com.example.appnghenhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class AdapterQuangCaoBaiHat extends RecyclerView.Adapter<AdapterQuangCaoBaiHat.ViewHolider> {

    Context context;
    ArrayList<BaiHat> arrayList;

    public AdapterQuangCaoBaiHat(Context context, ArrayList<BaiHat> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_baihatquangcao, parent, false);
        return new ViewHolider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolider holder, int position) {
        BaiHat baiHat = arrayList.get(position);
        holder.txtId.setText(position+1 +"");
        holder.txttencasi.setText(baiHat.getCaSi());
        holder.txtTenbaihat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolider extends RecyclerView.ViewHolder {
        TextView txtId, txtTenbaihat, txttencasi;
        ImageView imgHinh;

        public ViewHolider(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtID);
            txtTenbaihat = itemView.findViewById(R.id.txtTenbaihatQc);
            txttencasi = itemView.findViewById(R.id.txtTenCaSiQC);
            imgHinh = itemView.findViewById(R.id.imgHinhQC);
        }
    }
}
