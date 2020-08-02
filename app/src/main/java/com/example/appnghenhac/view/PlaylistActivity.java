package com.example.appnghenhac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.TheLoaiQCAPI;

public class PlaylistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TheLoaiQCAPI theLoaiQCAPI;
    Playlist playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        mapping();
        getIntentDL();
    }

    private void mapping() {
        recyclerView = findViewById(R.id.recycleDSPlaylist);
    }

    private void getIntentDL() {
        Intent intent = getIntent();
        if (intent != null)
           playlist = (Playlist) intent.getSerializableExtra("playlist");
    }
}