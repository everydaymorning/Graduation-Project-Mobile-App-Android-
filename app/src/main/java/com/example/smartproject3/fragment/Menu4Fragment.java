package com.example.smartproject3.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smartproject3.Comm_item;
import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.R;
import com.example.smartproject3.SettingItem;
import com.example.smartproject3.adapter.CommunityAdapter;
import com.example.smartproject3.adapter.CustomAdapter;

import java.util.ArrayList;

public class Menu4Fragment extends Fragment {

    private CommunityAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu4, container, false);

        adapter = new CommunityAdapter();
        listView = (ListView) fv.findViewById(R.id.comm_listview);

        listView.setAdapter(adapter);

        return fv;
    }

}
