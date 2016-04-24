package com.bionic.td_android.MainWindow.Overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bionic.td_android.MainWindow.MainActivity;
import com.bionic.td_android.R;

/**
 * Created by user on 29.03.2016.
 */
public class Overview_fragment extends Fragment {

    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void setUpTabs(View view){

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.fragment_overview_page_container);
        viewPager.setAdapter(new TabsAdapter(getChildFragmentManager(),
                getActivity()));


        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_overview_year_tab);
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
        configureToolbar(view);
        return view;
    }

    private void configureToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("Overview");

    }


    private void configurePage(View view){
        activity = (MainActivity) getActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                activity.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
