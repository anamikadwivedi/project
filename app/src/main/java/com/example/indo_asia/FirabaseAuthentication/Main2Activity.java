package com.example.indo_asia.FirabaseAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.indo_asia.R;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    private TextView tv1;
    private EditText mobile_no;
    private Spinner countrycode;
    private Button mSubmit;
    private String mCountryCode;
    private String mNumber;
    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.mobile_number);
        mobile_no = findViewById(R.id.et_phone_number);
        countrycode = findViewById(R.id.country_name);
        mSubmit = findViewById(R.id.submit_btn);

        countrycode.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountryCode = CountryData.countryAreaCodes[countrycode.getSelectedItemPosition()];
                mNumber = mobile_no.getText().toString().trim();

                if (mNumber.isEmpty() || mNumber.length() < 10) {
                    mobile_no.setError("Number is Required");
                    mobile_no.requestFocus();
                    return;
                }
                mPhoneNumber = "+" + mCountryCode + mNumber;
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("PhoneNumber", mPhoneNumber);
                startActivity(intent);
            }
        });
    }
}

  /*  @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(Main2Activity.this, MessagingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }*/





