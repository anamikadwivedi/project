package com.example.indo_asia.extraActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.indo_asia.R;

public class TermsAndCondition extends AppCompatActivity {
    CheckBox mBox;
    Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        mBox = (CheckBox) findViewById(R.id.checkBox1);
        String checkBoxText = "I agree to all the <a href='http://missindoasia.com.rules-and-regulation/.aspx' > Terms and Conditions</a>";

        mBox.setText(Html.fromHtml(checkBoxText));
        mBox.setMovementMethod(LinkMovementMethod.getInstance());
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBox.isChecked()){
                    mLoginButton.setVisibility(View.VISIBLE);
                }
                else{
                    mLoginButton.setVisibility(View.GONE);
                }
            }
        });
    }
}
