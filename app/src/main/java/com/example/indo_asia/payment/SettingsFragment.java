package com.example.indo_asia.payment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;


import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.indo_asia.R;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences preferences = getPreferenceManager().getSharedPreferences();
        onSharedPreferenceChanged(preferences, "paypal_intent_type");
        onSharedPreferenceChanged(preferences, "paypal_landing_page_type");
        onSharedPreferenceChanged(preferences, "paypal_payment_type");
        onSharedPreferenceChanged(preferences, "android_pay_currency");
        onSharedPreferenceChanged(preferences, "android_pay_allowed_countries_for_shipping");
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference instanceof ListPreference) {
            preference.setSummary(((ListPreference) preference).getEntry());
        }
    }
}