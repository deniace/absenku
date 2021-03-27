package com.ace.deni.absenku;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
////        final ActionBar actionBar = getActionBar();
//        getActionBar().setDisplayShowHomeEnabled(true);
    }
}
