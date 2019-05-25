package com.example.indo_asia.extraActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.indo_asia.R;

public class Sponsor extends AppCompatActivity {

    ImageView iv1;
    AnimationDrawable Anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        // Step1 : create the  RotateAnimation object
        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        // Step 2:  Set the Animation properties
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

        // Step 3: Start animating the image
        iv1.startAnimation(anim);

        // Later. if you want to  stop the animation
        // image.setAnimation(null);
    }
}