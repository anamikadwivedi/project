package com.example.indo_asia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.indo_asia.extraActivity.My_Profile;

import static androidx.recyclerview.widget.RecyclerView.*;

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<model> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }
    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {

        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    private class ViewHolder {
       /* private String location;
        private String DOB;
        private String Height;
        private String Weight;
        private String Bust;
        private String Waist;
        private String Hip;*/
       Button button;
        ImageView imageView;
        TextView txtName, txtemail, txtstate, txtcountry, txtcity, txtDob,txtlocation, txtHeight, txtWeight, txtBust, txtWaist, txtHip;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
           // holder.b=row.findViewById(R.id.btnshare);


            holder.txtName =  row.findViewById(R.id.txtname);
            holder.txtstate =  row.findViewById(R.id.txtstate);
            holder.txtcountry =  row.findViewById(R.id.txtCountry);
            holder.txtcity = row.findViewById(R.id.txtcity);
            holder.txtemail = row.findViewById(R.id.txtemail);
            holder.txtDob = row.findViewById(R.id.txtDOB);
            holder.txtlocation = row.findViewById(R.id.txtlocation);
            holder.txtHeight = row.findViewById(R.id.txtHeight);
            holder.txtWeight = row.findViewById(R.id.txtWeight);
            holder.txtBust = row.findViewById(R.id.txtBust);
            holder.txtWaist = row.findViewById(R.id.txtWaist);
            holder.txtHip = row.findViewById(R.id.txtHip);
            holder.imageView = row.findViewById(R.id.imgicon);

            // holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            row.setTag(holder);
            holder.button = row.findViewById(R.id.btnshare);
            holder.button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.
                    share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                    share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");

                    context.startActivity(Intent.createChooser(share, "Share link!"));
                }
            });
        }

        else {
            holder = (ViewHolder)row.getTag();
        }
        model model = recordList.get(i);
       // holder.id.setText(model.getId());
        holder.txtName.setText(model.getName());
        holder.txtemail.setText(model.getEmail());
        holder.txtstate.setText(model.getState());
        holder.txtcountry.setText(model.getCountry());
        holder.txtcity.setText(model.getCity());
        holder.txtDob.setText(model.getDOB());
        holder.txtlocation.setText(model.getLocation());
        holder.txtHeight.setText(model.getHeight());
        holder.txtWeight.setText(model.getWeight());
        holder.txtBust.setText(model.getBust());
        holder.txtWaist.setText(model.getWaist());
        holder.txtHip.setText(model.getHip());

        byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
