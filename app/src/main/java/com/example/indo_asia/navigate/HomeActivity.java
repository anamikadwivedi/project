package com.example.indo_asia.navigate;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.indo_asia.DatabaseHelper;
import com.example.indo_asia.DisplayAcivity;
import com.example.indo_asia.FirabaseAuthentication.Main2Activity;
import com.example.indo_asia.MainActivity;
import com.example.indo_asia.R;
import com.example.indo_asia.extraActivity.AboutUs;
import com.example.indo_asia.extraActivity.ContactUs;
import com.example.indo_asia.extraActivity.My_Profile;
import com.example.indo_asia.extraActivity.Photo_Gallery;
import com.example.indo_asia.extraActivity.Sponsor;
import com.example.indo_asia.fragment.ApplyNow;
import com.example.indo_asia.fragment.Contestants;
import com.example.indo_asia.fragment.Home;
import com.example.indo_asia.model;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;
import static com.example.indo_asia.MainActivity.mdatabasehelper;

public class HomeActivity extends AppCompatActivity  {


    SliderLayout sliderLayout;
    Button b1, b2, b3, b4;
    Toolbar toolbar;
    Context context;


    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Android toolbar
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
         //setActionBar(toolbar);

       // Bundle b = getIntent().getExtras();
       /* Cursor cursor = mdatabasehelper.getData("SELECT * FROM Star");

        while (cursor.moveToNext()) {
            eid = cursor.getInt(0);
            cname = cursor.getString(1);
            cage = cursor.getString(2);
            cphone = cursor.getString(3);
            cemail = cursor.getString(4);
            cdob = cursor.getString(5);
            clocatin = cursor.getString(6);
            cheight = cursor.getString(7);
            cweight = cursor.getString(8);
            cbust = cursor.getString(9);
            cwaist = cursor.getString(10);
            chip = cursor.getString(11);
            byte[] image = cursor.getBlob(12);*/

            //byte[] image  = cursor.getBlob(12);

        //}

        //  ArrayList.add(new model(id, name, age, phone, email, dob, location, height, Weight, bust, waist, hip, image));


        // }
        // mAdapter.notifyDataSetChanged();


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

                overridePendingTransition(R.anim.your_left_to_right, R.anim.your_right_to_left);



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
                Intent intent=new Intent(HomeActivity.this,ContactUs.class);
              //  startActivityForResult(intent, 2);
                startActivity(intent);


            }

        });
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

       /* if (id == R.id.new_activity) {

            Intent intent = new Intent(this, My_Profile.class);
            this.startActivity(intent);
            return true;
        }*/
        if (id == R.id.search_item) {

            Intent intent = new Intent(this, Photo_Gallery.class);
            this.startActivity(intent);
            return true;
        }
        if (id == R.id.print_item) {

            Intent intent = new Intent(this,DisplayAcivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, Sponsor.class);
            this.startActivity(intent);
            return true;
        }

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
                    sliderView.setImageDrawable(R.drawable.sheeny);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.pict);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.im);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.imagethree);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.sheeny);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription(" " + (i + 1));
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
