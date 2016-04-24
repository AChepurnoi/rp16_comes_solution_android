package com.bionic.td_android.MainWindow.Overview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user on 23.04.2016.
 */
public class TabsAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 8;

        private String tabTitles[] = new String[] { "2016", "2015","2014","2013","2012","2011","2010","2009"};
        private Context context;

        public TabsAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new PeriodOverview();
            Bundle bundle = new Bundle();
            bundle.putInt("year",Integer.parseInt(getPageTitle(position).toString()));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

}
