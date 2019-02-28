package com.example.smartproject3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.R;
import com.example.smartproject3.SettingItem;

import java.util.ArrayList;

public class SettingAdapter extends BaseAdapter {
    ArrayList<SettingItem> settingList = new ArrayList<SettingItem>();

    @Override
    public int getCount() {
        return settingList.size();
    }

    @Override
    public Object getItem(int position) {
        return settingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SettingHolder holder = new SettingHolder();
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_list, null, false);

            holder.title = (TextView) convertView.findViewById(R.id.setting_title);



            convertView.setTag(holder);
        }else{
            holder = (SettingHolder) convertView.getTag();
        }

        SettingItem item = settingList.get(position);

        holder.title.setText(item.getTitle());

        return convertView;
    }

    class SettingHolder{
        TextView title;
    }

    public void addItem(SettingItem settingItem) {
        settingList.add(settingItem);
    }
}
