package com.example.smartproject3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomList extends ArrayAdapter<String> {

    private String[] category;
    private Bitmap[] bitmaps;
    private String[] season;
    private Activity context;

    public CustomList(Activity context, String[] category, Bitmap[] bitmaps, String[] season) {
        super(context, R.layout.all_list_custom, category);
        this.category = category;
        this.bitmaps = bitmaps;
        this.season = season;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.all_list_custom, null, true);


        TextView textCategory = (TextView) listViewItem.findViewById(R.id.category);
        TextView textSeason = (TextView) listViewItem.findViewById(R.id.season);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageView);

        textCategory.setText(category[position]);
        textSeason.setText(season[position]);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,50,false));
        return  listViewItem;

    }
}
