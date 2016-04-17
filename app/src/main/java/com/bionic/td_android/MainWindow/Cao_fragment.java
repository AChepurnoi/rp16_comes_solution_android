package com.bionic.td_android.MainWindow;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bionic.td_android.R;

/**
 * Created by macbook on 4/9/16.
 */
public class Cao_fragment extends Fragment {

    private MainActivity activity;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        Button button= (Button) view.findViewById(R.id.button_cao);
        button.setOnClickListener((v) -> {


            try {
                String url = getResources().getString(R.string.CAOhref);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }catch (Exception e){
                Snackbar.make(v,"Please install web browser",Snackbar.LENGTH_LONG);
            }
        });

//        WebView webview = (WebView) view.findViewById(R.id.webview);
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
//                view.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf");
//                return true;
//            }
//        });

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
