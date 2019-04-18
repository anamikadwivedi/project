package com.example.indo_asia;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.indo_asia.fragment.AboutUs;
import com.example.indo_asia.fragment.ApplyNow;
import com.example.indo_asia.fragment.ContactUs;
import com.example.indo_asia.fragment.Contestants;
import com.example.indo_asia.fragment.Home;
import com.example.indo_asia.fragment.MeetSponsers;
import com.example.indo_asia.fragment.PhotoGallery;
import com.example.indo_asia.fragment.VideoGallery;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


public class DrawerAcivity extends AppCompatActivity   {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_acivity);

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

       /* viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

      ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/



        setupToolbar();

        DataModel[] drawerItem = new DataModel[8];

        drawerItem[0] = new DataModel(R.drawable.ic_home_black_24dp, "Home");
        drawerItem[1] = new DataModel(R.drawable.iconabout, "About_Us");
        drawerItem[2] = new DataModel(R.drawable.iconcall, "Contact");
        drawerItem[3] = new DataModel(R.drawable.iconsapply, "ApplyNow");
        drawerItem[4] = new DataModel(R.drawable.cunstomer, "Contestants");
        drawerItem[5] = new DataModel(R.drawable.iconsphotogallery, "Photo_Gallery");
        drawerItem[6] = new DataModel(R.drawable.iconsdocumentary, "Video_Gallery");
        drawerItem[7] = new DataModel(R.drawable.iconabout, "Meet_the_Sponsers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new Home();
                break;
            case 1:
                fragment = new AboutUs();
                break;
            case 2:
                fragment = new ContactUs();
                break;

            case 3:
                fragment = new ApplyNow();
                break;
            case 4:
                fragment = new Contestants();
                break;
            case 5:
                fragment = new VideoGallery();
                break;
            case 6:
                fragment = new PhotoGallery();
                break;
            case 7:
                fragment = new MeetSponsers();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }


}
    /*    drawerItem[0] = new DataModel(R.drawable.ic_home_black_24dp, "Home");
        drawerItem[1] = new DataModel(R.drawable.fixtures, "About_Us");
        drawerItem[2] = new DataModel(R.drawable.table, "Contact_Us");
        drawerItem[3] = new DataModel(R.drawable.connect, "ApplyNow");
        drawerItem[4] = new DataModel(R.drawable.connect, "Contestants");
        drawerItem[5] = new DataModel(R.drawable.fixtures, "Photo_Gallery");
        drawerItem[6] = new DataModel(R.drawable.table, "Video_Gallery");
        drawerItem[7] = new DataModel(R.drawable.table, "Meet_the_Sponsers");*/







///  private void selectItem(int position) {
//
//        Fragment fragment = null;
//
//        switch (position) {
//            case 0:
//                fragment = new Home();
//                break;
//            case 1:
//                fragment = new AboutUs();
//                break;
//            case 2:
//                fragment = new ContactUs();
//                break;
//
//            case 3:
//                fragment = new ApplyNow();
//                startActivity(new Intent(DrawerAcivity.this, MainActivity.class));
//                mDrawerLayout.closeDrawers();
//                break;
//            case 4:
//                fragment = new Contestants();
//                break;
//            case 5:
//                fragment = new PhotoGallery();
//                break;
//
//            case 6:
//                fragment = new VideoGallery();
//                break;
//
//            case 7:
//                    fragment = new MeetSponsers();
//
//                break;
//            default:
//                break;
//        }