package com.example.indo_asia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.indo_asia.payment.CardActivity;
import com.example.indo_asia.payment.MakePayment;
import com.example.indo_asia.payment.PayPalActivity;
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
                    Intent intent = new Intent(Splash.this, MakePayment.class);
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
