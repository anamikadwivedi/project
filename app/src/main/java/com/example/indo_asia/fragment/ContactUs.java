package com.example.indo_asia.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indo_asia.R;


public class ContactUs extends Fragment {
    public static ContactUs newInstance() {
        ContactUs fragment = new ContactUs();
        return fragment;
    }


    public ContactUs() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);


        return rootView;
    }

}