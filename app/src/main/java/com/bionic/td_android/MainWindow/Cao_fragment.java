package com.bionic.td_android.MainWindow;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bionic.td_android.R;

/**
 * Created by macbook on 4/9/16.
 */
public class Cao_fragment extends Fragment{

    private MainActivity activity;
    private Toolbar toolbar;
    private String url;
    private LinearLayout root;

    Button download;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        url = getResources().getString(R.string.CAOhref);
        View view = inflater.inflate(R.layout.fragment_cao, container, false);
        configurePage(view);
        return view;
    }

    private void configurePage(View view) {
        activity = (MainActivity) getActivity();
        configureToolbar(view);
        configureViews(view);
    }

    private void configureViews(View view) {

        WebView webview = (WebView) view.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        webview.getSettings().setBuiltInZoomControls(true);

    }

    private void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.simple_toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("CAO");
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
