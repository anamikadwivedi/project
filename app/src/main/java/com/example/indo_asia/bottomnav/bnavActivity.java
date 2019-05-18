package com.example.indo_asia.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indo_asia.DisplayAcivity;
import com.example.indo_asia.MainActivity;
import com.example.indo_asia.R;
import com.example.indo_asia.extraActivity.AboutUs;
import com.example.indo_asia.extraActivity.ContactUs;
import com.example.indo_asia.extraActivity.Photo_Gallery;
import com.example.indo_asia.fragment.Home;
import com.example.indo_asia.navigate.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class bnavActivity extends AppCompatActivity  {

    GridLayout gridLayout;
    SliderLayout sliderLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bnav);

        gridLayout=(GridLayout)findViewById(R.id.mainGrid);
        ImageButton aboutusbtn = (ImageButton) findViewById(R.id.abutbtn);
        ImageButton applybtn = (ImageButton) findViewById(R.id.applybtn);
        ImageButton contactbtn = (ImageButton) findViewById(R.id.contbtn);

        setSingleEvent(gridLayout);

        aboutusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bnavActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bnavActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(bnavActivity.this, ContactUs.class);
                startActivity(in);
            }
        });

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :
        setSliderViews();

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

            Intent intent = new Intent(this, DisplayAcivity.class);
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
                        Toast.makeText(bnavActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                    }
                });

                //at last add this view in your layout :
                sliderLayout.addSliderView(sliderView);
            }

        }


    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(bnavActivity.this,"Clicked at index "+ finalI,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}