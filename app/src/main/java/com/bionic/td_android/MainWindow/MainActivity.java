package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.bionic.td_android.R;

import java.util.Stack;

/**
 * Created by user on 18.03.2016.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment active;

    private Stack<Fragment> fragments = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fragment a = active = new Dashboard_fragment();
        active.setRetainInstance(true);
        fragments.add(a);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, active).addToBackStack(null).commit();

    }

    public void callFragment(Fragment fragment){

        active = fragment;
        fragments.add(active);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(active.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if(fragments.size() > 1){
//            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
            fragments.pop();
            active = fragments.peek();
        }

    }

}
