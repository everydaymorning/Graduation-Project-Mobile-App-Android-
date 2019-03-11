package com.example.smartproject3.fragment;

import android.content.Intent;
import android.icu.text.IDNA;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.smartproject3.MainActivity;
import com.example.smartproject3.Menu2Button.ClothesRegisterActivity;
import com.example.smartproject3.Menu2Button.InformationRegisterActivity;
import com.example.smartproject3.Menu2Button.ListActivity;
import com.example.smartproject3.R;
import com.example.smartproject3.adapter.TestAdapter;
import com.example.smartproject3.adapter.TestPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Menu2Fragment extends Fragment{
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;
    String userID;

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

        if(getActivity() != null && getActivity() instanceof MainActivity) {
            userID = ((MainActivity) getActivity()).getUserID();
        }
        Log.i("menu2ID", userID);
        TabLayout tabLayout = (TabLayout) fv.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        fab = (FloatingActionButton) fv.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) fv.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) fv.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) fv.findViewById(R.id.fab3);

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
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("userID", userID);

                //MainActivity에서 userID 받아온 뒤 intent로 ListActivity에 넘겨준다
                //NavigationView에서 맨 위에 userID + 님 환영합니다! 출력
                //userID를 통해 유저가 등록한 이미지 데이터를 받아와야함
                //다른 settingActivity도 마찬가지로 적용

                startActivity(intent);

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();

                Intent intent = new Intent(getActivity(), ClothesRegisterActivity.class);
                intent.putExtra("userID", userID);

                //MainActivity에서 userID 받아온 뒤 intent로 ListActivity에 넘겨준다
                //NavigationView에서 맨 위에 userID + 님 환영합니다! 출력
                //userID를 통해 유저가 등록한 이미지 데이터를 받아와야함
                //다른 settingActivity도 마찬가지로 적용

                startActivity(intent);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();

                Intent intent = new Intent(getActivity(), InformationRegisterActivity.class);
                intent.putExtra("userID", userID);

                //MainActivity에서 userID 받아온 뒤 intent로 ListActivity에 넘겨준다
                //NavigationView에서 맨 위에 userID + 님 환영합니다! 출력
                //userID를 통해 유저가 등록한 이미지 데이터를 받아와야함
                //다른 settingActivity도 마찬가지로 적용

                startActivity(intent);
            }
        });
        return fv;

    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
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

