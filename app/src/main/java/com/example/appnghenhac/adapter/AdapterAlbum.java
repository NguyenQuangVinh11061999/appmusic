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
import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.R;

import java.util.ArrayList;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.Viewholider> {
    Context context;
    ArrayList<Album> arrayList;

    public AdapterAlbum(Context context, ArrayList<Album> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album, parent, false);

        return new Viewholider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        Album album = arrayList.get(position);
        holder.txttenCaSi.setText(album.getTenCaSiAlbum());
        holder.txtTenAlbum.setText(album.getTenAlbum());

        Glide.with(context)
                .load(album.getHinhAlbum())
                .into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholider extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView txtTenAlbum, txttenCaSi;

        public Viewholider(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgAnhAlbum);
            txtTenAlbum = itemView.findViewById(R.id.txtTenAlbum);
            txttenCaSi = itemView.findViewById(R.id.txtTencaSi);
        }
    }
}
