package com.example.indo_asia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


import javax.net.ssl.HttpsURLConnection;

public class OTP extends AppCompatActivity {

   /* Button _btnVerOTP, _btnLogin;
    EditText _txtPhone,_txtverification;
    int randomNumber;*/

    PinEntryEditText pinEntry;

    /* <com.alimuzaffar.lib.pin.PinEntryEditText
        android:id="@+id/txt_pin_entry"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:cursorVisible="false"
        android:digits="1234567890"
        android:inputType="number"
        android:maxLength="4"
        android:textIsSelectable="false"
        android:textSize="20sp"
        app:pinAnimationType="popIn" />*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().equals("")) {
                        Toast.makeText(OTP.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OTP.this, "FAIL", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                    }
                }
            });
        }
    }
}

       /* StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        _txtPhone = (EditText) findViewById(R.id.phonetxt);
        _txtverification = (EditText) findViewById(R.id.phoneNo);
        _btnVerOTP = (Button)findViewById(R.id.verificationbtn);
        _btnLogin = (Button) findViewById(R.id.Login) ;
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String apiKey = "apikey" + "228707AKVXTwJt6Bo5b5c67d3";
                    Random random= new Random();
                    randomNumber=random.nextInt(999999);
                    String message = "&message" + "This is your message";
                    String sender = "&sender" + "Sheeny Star";
                   // String number = "&number" + "9137653853";

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("  http://smsp.myoperator.co/api/sendhttp.php?").openConnection();
                    String data = apiKey + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();
                    Toast.makeText(getApplicationContext(), "OTP SEND SUCCESSFULLY", Toast.LENGTH_LONG).show();

//return stringBuffer.toString();
                } catch (Exception e) {
//System.out.println("Error SMS "+e);
///return "Error "+e;
                    Toast.makeText(getApplicationContext(), "ERROR SMS "+e, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "ERROR "+e, Toast.LENGTH_LONG).show();
                }
            }
        });
        _btnVerOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomNumber==Integer.valueOf(_txtverification.getText().toString())){
                    Toast.makeText(getApplicationContext(), "You are logined successfully", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(), "WRONG OTP", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}*/
