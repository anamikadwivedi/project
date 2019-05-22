package com.example.indo_asia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indo_asia.payment.MakePayment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText mEditName, edcountry, edcity, edstate, edemail, eddob, edhif, edWeight, edBust, edWaist, edHip;
    Button mbuttonAdd, muttonList;
    ImageView mimageView;


    final int REQUEST_CODE_GALLERY = 999;
   // Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;


    public static DatabaseHelper mdatabasehelper;
    /*String[] countryNames={"India","China","Australia","Portugle","America","New Zealand"};
    int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.new_zealand};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("new Record");
        mEditName = findViewById(R.id.editname);
        edemail = findViewById(R.id.editEmailId);
        edstate = findViewById(R.id.editstate);
        edcountry = findViewById(R.id.editcountry);
        edcity = findViewById(R.id.editCity);
        eddob = findViewById(R.id.editDOB);
        edhif = findViewById(R.id.editHeight);
        edWeight = findViewById(R.id.editweight);
        edBust = findViewById(R.id.editbust);
        edWaist = findViewById(R.id.editwaist);
        edHip = findViewById(R.id.edithip);

        mbuttonAdd = findViewById(R.id.btnAdd);
        muttonList = findViewById(R.id.btnList);
        mimageView = findViewById(R.id.imageView);

        mdatabasehelper = new DatabaseHelper(this, "StarMake.sqlite", null, 1);
        //create table

        mdatabasehelper.queryData("CREATE TABLE IF NOT EXISTS SDB (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,  emailid VARCHAR, state VARCHAR, Country VARCHAR, city VARCHAR VARCHAR, dob VARCHAR, height VARCHAR, weight VARCHAR, bust VARCHAR, waist VARCHAR, hip VARCHAR, image BLOB)");

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
                            edemail.getText().toString().trim(),
                            eddob.getText().toString().trim(),
                            edstate.getText().toString().trim(),
                            edcountry.getText().toString().trim(),
                            edcity.getText().toString().trim(),
                            edhif.getText().toString().trim(),
                            edWeight.getText().toString().trim(),
                            edBust.getText().toString().trim(),
                            edWaist.getText().toString().trim(),
                            edHip.getText().toString().trim(),
                            imageViewToByte(mimageView)
                    );
                    Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MakePayment.class);
                    startActivity(intent);
                    mEditName.setText("");
                    edemail.setText("");
                    eddob.setText("");
                    edstate.setText("");
                    edcountry.setText("");
                    edcity.setText("");
                    edhif.setText("");
                    edWeight.setText("");
                    edBust.setText("");
                    edWaist.setText("");
                    edHip.setText("");
                    mimageView.setImageResource(R.drawable.addphoto);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Please Fill All Detail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Show
       /* muttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DisplayAcivity.class));

            }
        });*/

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

   /* public void SelectImage() {

        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                mimageView.setImageBitmap(bmp);

            } else if (requestCode == SELECT_FILE) {

                Uri selectedImageUri = data.getData();
                mimageView.setImageURI(selectedImageUri);
            }
        }*/
    }
}

