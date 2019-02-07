package com.example.smartproject3.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smartproject3.CustomDTO;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.CustomAdapter;

public class ChildOneFragment extends Fragment {

    private CustomAdapter adapter;
    private ListView listView;


    public static ChildOneFragment newInstance(){
        Bundle args = new Bundle();

        ChildOneFragment fragment = new ChildOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_child_one, container, false);

        adapter = new CustomAdapter();
        listView = (ListView) fv.findViewById(R.id.listView);

        setData();

        listView.setAdapter(adapter);

        return fv;
    }

    private void setData(){
        String[] titles = getResources().getStringArray(R.array.title);
        TypedArray back = getResources().obtainTypedArray(R.array.back);
        TypedArray forward = getResources().obtainTypedArray(R.array.forward);
        for(int i=0; i<titles.length;i++){
            CustomDTO dto = new CustomDTO();

            dto.setTitle(titles[i]);
            dto.setBackImage(back.getResourceId(i,0));
            dto.setForeImage(forward.getResourceId(i,0));

            adapter.addItem(dto);
        }
    }
}
