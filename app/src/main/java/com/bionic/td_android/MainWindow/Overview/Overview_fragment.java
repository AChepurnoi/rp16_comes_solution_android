package com.bionic.td_android.MainWindow.Overview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.R;

/**
 * Created by user on 29.03.2016.
 */
public class Overview_fragment extends Fragment {




    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 8;

        private String tabTitles[] = new String[] { "2016", "2015","2014","2013","2012","2011","2010","2009"};
        private Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:

//                    return
                case 1:
//                    return new TakenOrders();
                case 2:
//                    return new MyOrders();
            }
            return new Weekly_overview_fragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }


    private MainActivity activity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void setUpTabs(View view){

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.fragment_overview_page_container);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager(),
                getActivity()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_overview_sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_overview, container, false);
        configurePage(view);
        setUpTabs(view);
        return view;
    }

    private void configurePage(View view){

        activity = (MainActivity) getActivity();


    }
}
