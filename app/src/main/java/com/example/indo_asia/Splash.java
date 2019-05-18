package com.example.indo_asia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.indo_asia.FirabaseAuthentication.FAuth;
import com.example.indo_asia.bottomnav.bnavActivity;
import com.example.indo_asia.bottomnav.bottomnav;
import com.example.indo_asia.extraActivity.TermsAndCondition;
import com.example.indo_asia.extraActivity.Video;
import com.example.indo_asia.extraActivity.Video_Gallery;
import com.example.indo_asia.fragment.Home;
import com.example.indo_asia.navigate.HomeActivity;
import com.example.indo_asia.payment.payment_Activity;
import com.paypal.android.sdk.payments.PaymentActivity;
//import com.example.indo_asia.navigtation.navActivity;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       /* final ImageView zoom = (ImageView) findViewById(R.id.imageView);

        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        zoom.startAnimation(zoomAnimation);*/

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splash.this, basic.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
