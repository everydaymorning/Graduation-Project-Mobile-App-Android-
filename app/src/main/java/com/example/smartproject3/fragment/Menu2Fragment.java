package com.example.smartproject3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.smartproject3.MainActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.TestAdapter;
import com.example.smartproject3.adapter.TestPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Menu2Fragment extends Fragment{
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;

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

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        fab = (FloatingActionButton) fv.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) fv.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) fv.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();

            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });

        return fv;
    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        TestAdapter adapter = new TestAdapter(getChildFragmentManager());
        adapter.addFragment(new ChildOneFragment(), "오늘의 옷");
        adapter.addFragment(new ChildTwoFragment(), "오늘의 item");
        viewPager.setAdapter(adapter);



    }


}

