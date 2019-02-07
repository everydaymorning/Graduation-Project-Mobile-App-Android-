package com.example.smartproject3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smartproject3.MainActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.TestAdapter;
import com.example.smartproject3.adapter.TestPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Menu2Fragment extends Fragment{

    public static Menu2Fragment newInstance() {
        return new Menu2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fv = inflater.inflate(R.layout.fragment_menu2, container, false);
        ViewPager viewPager = (ViewPager) fv.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) fv.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        return fv;
    }

    private void setupViewPager(ViewPager viewPager) {

        TestAdapter adapter = new TestAdapter(getChildFragmentManager());
        adapter.addFragment(new ChildOneFragment(), "오늘의 옷");
        adapter.addFragment(new ChildTwoFragment(), "오늘의 item");
        viewPager.setAdapter(adapter);



    }


}

