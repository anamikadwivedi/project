package com.example.indo_asia.navigate;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indo_asia.DatabaseHelper;
import com.example.indo_asia.DisplayAcivity;
import com.example.indo_asia.MainActivity;
import com.example.indo_asia.R;
import com.example.indo_asia.extraActivity.AboutUs;
import com.example.indo_asia.extraActivity.ContactUs;
import com.example.indo_asia.fragment.ApplyNow;
import com.example.indo_asia.fragment.Contestants;
import com.example.indo_asia.fragment.Home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity  {

    SliderLayout sliderLayout;
    Button b1, b2, b3, b4;
    Toolbar toolbar;

    EditText mEditName,mEditAge,mEditphone,edlocation,edemail,eddob,edhif,edWeight,edBust,edWaist,edHip;
    ImageView imageView;
    SQLiteDatabase mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Android toolbar
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         setActionBar(toolbar);

        //setSupportActionBar(toolbar);

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :
        setSliderViews();

        b1 = findViewById(R.id.btnhome);
        b2 = findViewById(R.id.btnAbout);
        b3 = findViewById(R.id.buttonApply);
        b4 = findViewById(R.id.btnContact);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayAcivity.class);
                //Create a bundle object
                Bundle b = new Bundle();
              //  EditText mEditName,mEditAge,mEditphone,edlocation,edemail,eddob,edhif,edWeight,edBust,edWaist,edHip;

                //Inserts a String value into the mapping of this Bundle
                b.putString("name", mEditName.getText().toString());
                b.putString("age", mEditAge.getText().toString());
                b.putString("Phone", mEditphone.getText().toString());
                b.putString("location", edlocation.getText().toString());
                b.putString("email", edemail.getText().toString());
                b.putString("dob", eddob.getText().toString());
                b.putString("Height", edhif.getText().toString());
                b.putString("weight", edWeight.getText().toString());
                b.putString("Bust",edBust.getText().toString());
                b.putString("Waist", edWaist.getText().toString());
                b.putString("Hip", edHip.getText().toString());



                //Add the bundle to the intent.
                intent.putExtras(b);

                //start the DisplayActivity
                startActivity(intent);
            }

        });
    }

    private void setActionBar(Toolbar toolbar) {

    }


    //MENU OPTION
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.new_activity) {

            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.search_item) {

            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.upload_item) {

            Intent intent = new Intent(this,AboutUs.class);
            this.startActivity(intent);
            return true;
        }

        /*if (id == R.id.action_search) {
            Toast.makeText(this, "Android Menu is Clicked", Toast.LENGTH_LONG).show();
            return true;
        }*/

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Android Menu is Clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//MENU OPTION

    private void setSliderViews() {

        for (int i = 0; i <= 4; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.imagefour);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.imagetwo);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.im);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.imagethree);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.ic_slider_3);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" +
                    "Jackdaws love my big sphinx of quartz. " + (i + 1));
            final int finalI = i;

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(HomeActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }



    }
}
