package com.example.indo_asia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.indo_asia.extraActivity.ShareActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DisplayAcivity extends AppCompatActivity {

   /* ListView mlistview;
    ArrayList<model> mlist;
    RecordListAdapter mAdapter;
    ImageView imageViewicon;*/

    ListView mlistview;
    ArrayList<model> list;
    RecordListAdapter mAdapter = null;
    ImageView imageViewicon;
    Button sharebuttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_acivity);

        mlistview = (ListView) findViewById(R.id.mlistview);
        list = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, list);
        mlistview.setAdapter(mAdapter);

        sharebuttn = (Button)findViewById(R.id.btnshare);

        sharebuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayAcivity.this, ShareActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // get all data from sqlite
        Cursor cursor = MainActivity.mdatabasehelper.getData("SELECT * FROM Star");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String dob = cursor.getString(5);
            String location = cursor.getString(6);
            String height = cursor.getString(7);
            String Weight = cursor.getString(8);
            String bust = cursor.getString(9);
            String waist = cursor.getString(10);
            String hip = cursor.getString(11);

            byte[] image = cursor.getBlob(12);

            list.add(new model(id, name, age, phone, email, dob, location, height, Weight, bust, waist, hip, image));
        }
        mAdapter.notifyDataSetChanged();

        mlistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(DisplayAcivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = MainActivity.mdatabasehelper.getData("SELECT id FROM Star");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(DisplayAcivity.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = MainActivity.mdatabasehelper.getData("SELECT id FROM Star");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

   // ImageView imageViewicon;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        imageViewicon = dialog.findViewById(R.id.imageviewRecord);
        final EditText editname = dialog.findViewById(R.id.editName);
        final EditText editage = dialog.findViewById(R.id.updateAge);
        final EditText editemail = dialog.findViewById(R.id.editEmailId);
        final EditText editPhone = dialog.findViewById(R.id.editPhone);
        final EditText editlocation = dialog.findViewById(R.id.editLocation);
        final EditText editdob = dialog.findViewById(R.id.editDateOfBirth);
        final EditText editheight = dialog.findViewById(R.id.editHeight);
        final EditText editweight = dialog.findViewById(R.id.editWeight);
        final EditText editbust = dialog.findViewById(R.id.editBust);
        final EditText editwaist = dialog.findViewById(R.id.editWaist);
        final EditText edithip = dialog.findViewById(R.id.editHip);

        Button btnupdate = dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        DisplayAcivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.mdatabasehelper.updateData(
                            editname.getText().toString().trim(),
                            editage.getText().toString().trim(),
                            editemail.getText().toString().trim(),
                            editPhone.getText().toString().trim(),
                            editlocation.getText().toString().trim(),
                            editdob.getText().toString().trim(),
                            editheight.getText().toString().trim(),
                            editweight.getText().toString().trim(),
                            editbust.getText().toString().trim(),
                            editwaist.getText().toString().trim(),
                            edithip.getText().toString().trim(),

                            MainActivity.imageViewToByte(imageViewicon),
                            position
                    );
                   // dialog.show();
                    Toast.makeText(getApplicationContext(), "Update Sucessfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateRecordList();
            }
        });
    }

    private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(DisplayAcivity.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.mdatabasehelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateRecordList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateRecordList() {
        Cursor cursor = MainActivity.mdatabasehelper.getData("SELECT * FROM Star");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String dob = cursor.getString(5);
            String location = cursor.getString(6);
            String height = cursor.getString(7);
            String Weight = cursor.getString(8);
            String bust = cursor.getString(9);
            String waist = cursor.getString(10);
            String hip = cursor.getString(11);

            byte[] image = cursor.getBlob(12);

            list.add(new model(id, name, age, phone, email, dob, location, height, Weight, bust, waist, hip, image));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewicon.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

// OLD CODE***********************************
        // ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Record List");
        // to enable back button in action bar


       // mlistview = findViewById(R.id.mlistview);

       // mlist = new ArrayList<model>();
       // mAdapter = new RecordListAdapter(this, R.layout.row, mlist);
       // mlistview.setAdapter(mAdapter);

     /*  mEditName.getText().toString().trim(),
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
                imageViewToByte(mimageView)*/
        //get all data from database

       /* Cursor cursor = MainActivity.mdatabasehelper.getData("SELECT * FROM Star");
        mlist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String dob = cursor.getString(5);
            String location = cursor.getString(6);
            String height = cursor.getString(7);
            String Weight = cursor.getString(8);
            String bust = cursor.getString(9);
            String waist = cursor.getString(10);
            String hip = cursor.getString(11);

            byte[] image = cursor.getBlob(12);

            mlist.add(new model(id, name, age, phone, email, dob, location, height, Weight, bust, waist, hip, image));
            //Toast.makeText(this, "Record found", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();
        if (mlist.size() == 0) {
            //if
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }

        mlistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //alert dialoge to display options of update and delete
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(DisplayAcivity.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            Cursor c = MainActivity.mdatabasehelper.getData("SELECT ID FROM Star");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            //show update diaog
                            showDialogUpdate(DisplayAcivity.this, arrID.get(position));
                        }
                        if (i == 1) {
                            //delete
                            Cursor c = MainActivity.mdatabasehelper.getData("SELECT ID FROM Star");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(DisplayAcivity.this);
        dialogDelete.setTitle("Warning");
        dialogDelete.setMessage("Are You Want To Delete");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    MainActivity.mdatabasehelper.deleteData(idRecord);
                    Toast.makeText(DisplayAcivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }
//For Update Record
  private void showDialogUpdate(Activity activity, final int position) {
          final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        imageViewicon = dialog.findViewById(R.id.imageviewRecord);
           final EditText editname = dialog.findViewById(R.id.editName);
          final EditText editage = dialog.findViewById(R.id.ediAge);
         final EditText editemail = dialog.findViewById(R.id.editEmailId);
          final EditText editPhone = dialog.findViewById(R.id.editPhone);
          final EditText editlocation = dialog.findViewById(R.id.editLocation);
          final EditText editdob = dialog.findViewById(R.id.editDateOfBirth);
          final EditText editheight = dialog.findViewById(R.id.editHeight);
          final EditText editweight = dialog.findViewById(R.id.editWeight);
          final EditText editbust = dialog.findViewById(R.id.editBust);
          final EditText editwaist = dialog.findViewById(R.id.editWaist);
          final EditText edithip = dialog.findViewById(R.id.editHip);

         Button btnupdate = dialog.findViewById(R.id.btnUpdate);

        int Width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        //set hiegt
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(Width, height);
        dialog.show();

        //in update dialoge click image viewbto update image
        imageViewicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storage
                ActivityCompat.requestPermissions(
                        DisplayAcivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.mdatabasehelper.updateData(
                            editname.getText().toString().trim(),
                            editage.getText().toString().trim(),
                            editemail.getText().toString().trim(),
                            editPhone.getText().toString().trim(),
                            editlocation.getText().toString().trim(),
                            editdob.getText().toString().trim(),
                            editheight.getText().toString().trim(),
                            editweight.getText().toString().trim(),
                            editbust.getText().toString().trim(),
                            editwaist.getText().toString().trim(),
                            edithip.getText().toString().trim(),

                            MainActivity.imageViewToByte(imageViewicon),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Sucessfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateRecordList();
            }
        });
    }

    private void updateRecordList() {
        Cursor cursor = MainActivity.mdatabasehelper.getData("SELECT * FROM Star");
        mlist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String dob = cursor.getString(5);
            String location = cursor.getString(6);
            String height = cursor.getString(7);
            String Weight = cursor.getString(8);
            String bust = cursor.getString(9);
            String waist = cursor.getString(10);
            String hip = cursor.getString(11);

            byte[] image = cursor.getBlob(12);

            mlist.add(new model(id, name, age, phone, email, dob, location, height, Weight, bust, waist, hip, image));
        }
        mAdapter.notifyDataSetChanged();
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
        if (requestCode == 888)
        {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 888);
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

        if (requestCode == 888 && resultCode == RESULT_OK)
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
                imageViewicon.setImageURI(resulturi);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}*/
