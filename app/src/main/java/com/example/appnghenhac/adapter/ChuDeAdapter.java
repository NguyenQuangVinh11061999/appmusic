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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.R;
import com.example.appnghenhac.view.MainActivity;
import com.example.appnghenhac.view.TheLoaiActivity;

import java.util.ArrayList;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHoLiDay> {
    Context context;
    ArrayList<ChuDe> arrayList;
    public View view;
    int id = 0;

    public ChuDeAdapter(Context context, ArrayList<ChuDe> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHoLiDay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_chude, parent, false);

        return new ViewHoLiDay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoLiDay holder, int position) {
        final ChuDe chuDe = arrayList.get(position);
        holder.txtTenChuDe.setText(chuDe.getTenChuDe());

        Glide.with(context)
                .load(chuDe.getHinhChuDe())
                .into(holder.imghinhchude);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = chuDe.getId();
                Intent intent = new Intent(context, TheLoaiActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoLiDay extends RecyclerView.ViewHolder {
        ImageView imghinhchude;
        TextView txtTenChuDe;
        public ViewHoLiDay(@NonNull final View itemView) {
            super(itemView);
            imghinhchude = itemView.findViewById(R.id.imgHinhChuDe);
            txtTenChuDe = itemView.findViewById(R.id.txttenChuDe);
            view = itemView;
        }
    }
}
