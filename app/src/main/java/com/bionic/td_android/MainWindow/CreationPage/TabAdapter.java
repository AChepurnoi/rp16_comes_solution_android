package com.bionic.td_android.MainWindow.CreationPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bionic.td_android.MainWindow.CreationPage.CreateShift.Shift_fragment;
import com.bionic.td_android.MainWindow.CreationPage.Daytypes.DayTypeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Granium on 02.06.16.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new Shift_fragment());
        fragments.add(new DayTypeFragment());
    }

    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Create shift";
            case 1:
                return "Add dayptype";
            default:
//                return "Create shift";
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
