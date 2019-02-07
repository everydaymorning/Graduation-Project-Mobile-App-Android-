package com.example.smartproject3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartproject3.Comm_item;
import com.example.smartproject3.R;

import java.util.ArrayList;

public class CommunityAdapter extends BaseAdapter {

    ArrayList<Comm_item> comm_list = new ArrayList<Comm_item>();
    TextView comm_title;
    TextView comm_place;
    TextView comm_type;
    TextView comm_content;
    TextView comm_id;
    TextView comm_time;
    @Override
    public int getCount() {
        return comm_list.size();
    }

    @Override
    public Object getItem(int position) {
        return comm_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comm_item, null, false);

            comm_title = (TextView) convertView.findViewById(R.id.comm_title);
            comm_place = (TextView) convertView.findViewById(R.id.comm_place);
            comm_type = (TextView) convertView.findViewById(R.id.comm_type);
            comm_content = (TextView) convertView.findViewById(R.id.comm_content);
            comm_id = (TextView) convertView.findViewById(R.id.comm_id);
            comm_time = (TextView) convertView.findViewById(R.id.comm_time);

            comm_title.setText(comm_list.get(position).getComm_title());
            comm_place.setText(comm_list.get(position).getComm_place());
            comm_type.setText(comm_list.get(position).getComm_type());
            comm_content.setText(comm_list.get(position).getComm_content());
            comm_id.setText(comm_list.get(position).getComm_id());
            comm_time.setText(comm_list.get(position).getComm_time());

            return convertView;
        }
        return null;
    }
}
