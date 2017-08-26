package com.example.biryuk.apo_app_and;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import layout.InfoFragment;
import layout.ReportFragment;
import layout.SalaryFragment;
import layout.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private Fragment fragment;
    private FragmentManager fragmentManager;


    public void onLogout(View view) {
        Data.SetToken(null);
        startActivity(new Intent(this, ChooseRoleActivity.class));
        finish();;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {

                case R.id.navigation_info:
                    /*
                    mTextMessage.setText("Инфо");
                    switchToFragment();
                    return true;*/
                    selectedFragment = InfoFragment.newInstance();
                    break;
                case R.id.navigation_report:
                    selectedFragment = ReportFragment.newInstance();
                    break;
                case R.id.navigation_salary:
                    selectedFragment = SalaryFragment.newInstance();
                    break;
                case R.id.navigation_settings:
                    selectedFragment = SettingsFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Info, selectedFragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment firstFragment = InfoFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Info, firstFragment).commit();


        fragmentManager = getSupportFragmentManager();


        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
