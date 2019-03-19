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

    String userID;
    Button regButton;
    Button getAllButton;
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


        if (getActivity() != null && getActivity() instanceof MainActivity) {
            userID = ((MainActivity) getActivity()).getUserID();
        }

        regButton = (Button) fv.findViewById(R.id.regButton);
        getAllButton = (Button) fv.findViewById(R.id.getAllButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClothesRegisterActivity.class);
                intent.putExtra("userID", userID);

                //MainActivity에서 userID 받아온 뒤 intent로 ListActivity에 넘겨준다
                //NavigationView에서 맨 위에 userID + 님 환영합니다! 출력
                //userID를 통해 유저가 등록한 이미지 데이터를 받아와야함
                //다른 settingActivity도 마찬가지로 적용

                startActivity(intent);
            }
        });

        getAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
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
}

