package com.example.indo_asia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText mEditName,mEditAge,mEditphone,edlocation,edemail,eddob,edhif,edWeight,edBust,edWaist,edHip;
    Button mbuttonAdd,muttonList;
      ImageView  mimageView;

      final int REQUEST_CODE_GALLERY =999;

      public static  DatabaseHelper mdatabasehelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("new Record");


        mEditName = findViewById(R.id.editname);
        mEditAge = findViewById(R.id.ediAge);
        edemail = findViewById(R.id.editEmailId);
        mEditphone = findViewById(R.id.editmoileno);
        edlocation = findViewById(R.id.editlocation);
        eddob = findViewById(R.id.editDOB);
        edhif = findViewById(R.id.editHeight);
        edWeight = findViewById(R.id.editweight);
        edBust = findViewById(R.id.editbust);
        edWaist = findViewById(R.id.editwaist);
        edHip = findViewById(R.id.edithip);

        mbuttonAdd = findViewById(R.id.btnAdd);
        muttonList = findViewById(R.id.btnList);
        mimageView = findViewById(R.id.imageView);

        mdatabasehelper = new DatabaseHelper(this, "SheenyStar.sqlite", null, 1);
        //create table

        mdatabasehelper.queryData("CREATE TABLE IF NOT EXISTS Star (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age VARCHAR, emailid VARCHAR, phone VARCHAR, location VARCHAR, dob VARCHAR, height VARCHAR, weight VARCHAR, bust VARCHAR, waist VARCHAR, hip VARCHAR, image BLOB)");

        mimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        });
        //add Record
        mbuttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mdatabasehelper.insertData(
                            mEditName.getText().toString().trim(),
                            mEditAge.getText().toString().trim(),
                            mEditphone.getText().toString().trim(),
                            edemail.getText().toString().trim(),
                            eddob.getText().toString().trim(),
                            edlocation.getText().toString().trim(),
                            edhif.getText().toString().trim(),
                            edWeight.getText().toString().trim(),
                            edBust.getText().toString().trim(),
                            edWaist.getText().toString().trim(),
                            edHip.getText().toString().trim(),
                            imageViewToByte(mimageView)
                    );
                    Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    mEditName.setText("");
                    mEditAge.setText("");
                    mEditphone.setText("");
                    edemail.setText("");
                    eddob.setText("");
                    edlocation.setText("");
                    edhif.setText("");
                    edWeight.setText("");
                    edBust.setText("");
                    edWaist.setText("");
                    edHip.setText("");
                    mimageView.setImageResource(R.drawable.addphoto);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        //Show
        muttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DisplayAcivity.class));

            }
        });

    }

    public static byte[] imageViewToByte(ImageView mimageView) {
        Bitmap bitmap = ((BitmapDrawable)mimageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK)
        {
            Uri imageuri  = data.getData();
            CropImage.activity(imageuri)
                    .setGuidelines(CropImageView.Guidelines.ON)//enable images
            .setAspectRatio(1,1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                Uri resulturi = result.getUri();
                mimageView.setImageURI(resulturi);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
