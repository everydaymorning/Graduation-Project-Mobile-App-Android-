package com.example.smartproject3.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.smartproject3.fragment.ChildOneFragment;
import com.example.smartproject3.fragment.ChildTwoFragment;

public class TestPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 2;

    public TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return ChildOneFragment.newInstance();
            case 1:
                return ChildTwoFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "오늘의 옷";
            case 1:
                return "오늘의 item";
                default:
                    return null;
        }
    }
}
