package com.example.smartproject3.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.smartproject3.Comm_item;
import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.MainActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.SettingItem;
import com.example.smartproject3.WriteActivity;
import com.example.smartproject3.adapter.CommunityAdapter;
import com.example.smartproject3.adapter.CustomAdapter;
import com.example.smartproject3.request.WriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Menu4Fragment extends Fragment {

    private CommunityAdapter adapter;
    private ListView listView;
    FloatingActionButton writeButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu4, container, false);

        writeButton = (FloatingActionButton) fv.findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });

        return fv;
    }

}
