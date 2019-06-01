package com.kys.lg.testscheduler;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:return new main2_fragment();
            case 1:return new main3_fragment();
            case 2:return  new main4_fragment();
            case 3:return  new main5_fragment();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "오늘";
            case 1:return "내일";
            case 2:return "시험 일정";
            case 3:return "공부 팁";
            default:return null;

        }
    }
}
