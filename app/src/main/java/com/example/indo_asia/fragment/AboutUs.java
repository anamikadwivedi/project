package com.example.indo_asia.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.indo_asia.R;


public class AboutUs extends Fragment {

    public static AboutUs newInstance() {
        AboutUs fragment = new AboutUs();
        return fragment;
    }


    public AboutUs() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        TextView txtOne = (TextView) rootView.findViewById(R.id.textView1);
        txtOne.setText("Conceptualized and produced by Sheeny Star Maker Pvt. Ltd. and supported by the key members of the Beauty fraternity, MIA is the most appreciated Asia and a global platform that gives the Indian fashion/ film fraternity an opportunity to reach audiences in international territories. The alliances made via the platform of MIA provide huge benefits and gain to India but an equally important objective of this prestigious brand is to create similar outcomes in its host country.  The aim is to establish a system of mutual benefit to both India as well as the host destinations by boosting tourism, economic development, trade, culture, cross-border investments and fashion/ film co-productions.\n" +
                "\n" +
                "Sheeny Star Maker Pvt. Ltd promotes the MIA Weekend & TIA globally each year and travel to new, exciting and beautiful destinations, taking the Beauty fraternity with it to unite and celebrate the best of Indian film and culture, thereby taking Indian Cinema and India to a wider audience.");


        return rootView;


    }

}