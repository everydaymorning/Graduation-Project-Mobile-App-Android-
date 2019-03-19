package com.example.smartproject3.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.CustomAdapter;

public class ChildTwoFragment extends Fragment {

    private CustomAdapter adapter;
    private ListView listView;

    public static ChildTwoFragment newInstance()
    {
        Bundle args = new Bundle();

        ChildTwoFragment fragment = new ChildTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_child_two, container, false);



        return fv;
    }


}
