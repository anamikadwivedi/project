package com.example.indo_asia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

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
        ImageView imageView;
        TextView txtName, txtage,txtphone,txtemail,  txtDob,txtlocation, txtHeight, txtWeight, txtBust, txtWaist, txtHip;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName =  row.findViewById(R.id.txtname);
            holder.txtage =  row.findViewById(R.id.txtage);
            holder.txtphone =  row.findViewById(R.id.txtphone);
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
        }

        else {
            holder = (ViewHolder)row.getTag();
        }
        model model = recordList.get(i);

        holder.txtName.setText(model.getName());
        holder.txtage.setText(model.getAge());
        holder.txtphone.setText(model.getPhone());
        holder.txtemail.setText(model.getEmail());
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
