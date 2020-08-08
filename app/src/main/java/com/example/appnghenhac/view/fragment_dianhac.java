package com.example.appnghenhac.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.Model.ImageConverter;
import com.example.appnghenhac.R;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_dianhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dianhac,container,false);
        circleImageView = view.findViewById(R.id.img_dianhac);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        Animation animFade = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);

        Bundle bundle = getArguments();
        if (bundle != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
            String tg = simpleDateFormat.format(bundle.getInt("thoigian"));
            Toast.makeText(getContext(), ""+tg, Toast.LENGTH_SHORT).show();
        }

        animFade.setDuration(30000);
        circleImageView.startAnimation(animFade);
        return view;
    }

    public  void PlayHinh(String hinh){
        Glide.with(getContext())
                .load(hinh)
                .apply(RequestOptions.circleCropTransform())
                .into(circleImageView);
    }
}
