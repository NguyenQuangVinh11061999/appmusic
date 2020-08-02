package com.example.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.view.PlaylistActivity;
import com.example.appnghenhac.R;

import java.util.List;

public class PlaylistAdapter extends BaseAdapter {
    Context context;
    List<Playlist> list;

    public PlaylistAdapter(Context context, List<Playlist> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHoLiday {
        ImageView imgHinhAnh, imgHinhicon;
        TextView txtTen;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoLiday hoLiday = null;
        if (hoLiday == null) {
            hoLiday = new ViewHoLiday();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_playlist, null);

            hoLiday.imgHinhAnh = convertView.findViewById(R.id.imgHinhPlaylist);
            hoLiday.imgHinhicon = convertView.findViewById(R.id.imgHinhicon);
            hoLiday.txtTen = convertView.findViewById(R.id.txtTen);

            convertView.setTag(hoLiday);
        } else {
            hoLiday = (ViewHoLiday) convertView.getTag();
        }

        Playlist playlist = list.get(position);

        hoLiday.txtTen.setText(playlist.getTen());
        Glide.with(context)
                .load(playlist.getHinhAnh())
                .into(hoLiday.imgHinhAnh);
        Glide.with(context)
                .load(playlist.getHinhicon())
                .into(hoLiday.imgHinhicon);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("playlist",list.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
