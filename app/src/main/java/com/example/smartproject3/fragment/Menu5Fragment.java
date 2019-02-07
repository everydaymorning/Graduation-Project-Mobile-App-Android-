package com.example.smartproject3.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.R;
import com.example.smartproject3.SettingItem;
import com.example.smartproject3.adapter.CustomAdapter;
import com.example.smartproject3.adapter.SettingAdapter;
import com.example.smartproject3.setting_activity.SettingList1;
import com.example.smartproject3.setting_activity.SettingList2;
import com.example.smartproject3.setting_activity.SettingList3;
import com.example.smartproject3.setting_activity.SettingList4;

public class Menu5Fragment extends Fragment {
    static final Class<?>[] ACTIVITIES = {SettingList1.class, SettingList2.class, SettingList3.class, SettingList4.class};
    private SettingAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu5, container, false);

        adapter = new SettingAdapter();
        listView = (ListView) fv.findViewById(R.id.setting_listview);

        setData();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ACTIVITIES[position]);
                startActivity(intent);
            }
        });
        return fv;
    }

    private void setData(){
        String[] titles = getResources().getStringArray(R.array.setting);
        for(int i=0; i<titles.length;i++){
            SettingItem item = new SettingItem();

            item.setTitle(titles[i]);

            adapter.addItem(item);
        }
    }
}
