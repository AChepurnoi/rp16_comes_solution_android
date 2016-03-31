package com.bionic.td_android.MainWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.bionic.td_android.Entity.User;
import com.bionic.td_android.R;
import com.bionic.td_android.Utility.EntitySaver;

import java.util.Stack;

/**
 * Created by user on 18.03.2016.
 */
public class MainActivity extends AppCompatActivity {

    private Fragment active;
    private User user;

    private Stack<Fragment> fragments = new Stack<>();

    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fragment a = active = new Dashboard_fragment();
        active.setRetainInstance(true);
        fragments.add(a);

//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            if(bundle.getString(Intent.EXTRA_TEXT) != null) {
//                try {
//                    user = new ObjectMapper().readValue(bundle.getString(Intent.EXTRA_TEXT),User.class);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e("Bionic","Error parsing User in Main Activity");
//                }
//                getIntent().removeExtra(Intent.EXTRA_TEXT);
//
//            }
//        }
        user = EntitySaver.getUser();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, active).addToBackStack(null).commit();

    }


    public void overview(){

        Fragment a = active = new Overview_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void personal_information(){

        Fragment a = active = new Account_fragment.PersonalInformation_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void work_information(){

        Fragment a = active = new Account_fragment.WorkInformation_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void change_password(){

        Fragment a = active = new Account_fragment.PersonalInformation_fragment.ChangePassword_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
    }



    @Override
    protected void onResume() {
        super.onResume();


    }

    public void my_account(){

        Fragment a = active = new Account_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        ((Account_fragment)active).setUser(user);

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    public void new_shift() {
        Fragment a = active = new Shift_fragment();
        fragments.add(a);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, active);
        transaction.addToBackStack(null);

        transaction.commit();
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

    public User getUser() {
        return user;
    }
}
