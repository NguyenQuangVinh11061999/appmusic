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
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.Viewholider>  {

    Context context;
    ArrayList<TheLoai> arrayList;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_theloai, parent, false);

        return new Viewholider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        final TheLoai theloai = arrayList.get(position);
        holder.txttentheLoai.setText(theloai.getTenTheLoai());

        Glide.with(context)
                .load(theloai.getHinhTheLoai())
                .into(holder.imghinhtheloai);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholider extends RecyclerView.ViewHolder {
        ImageView imghinhtheloai;
        TextView txttentheLoai;
        public Viewholider(@NonNull View itemView) {
            super(itemView);
            imghinhtheloai = itemView.findViewById(R.id.imghinhtheloai);
            txttentheLoai= itemView.findViewById(R.id.txtTenTheLoai);
        }
    }
}
