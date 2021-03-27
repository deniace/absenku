package com.ace.deni.absenku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearProfile, linearKalender, linearKelas, linearAbsen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearProfile = findViewById(R.id.linear_profile);
        linearProfile.setOnClickListener(goToProfile);

        linearKalender = findViewById(R.id.linear_kalender);
        linearKalender.setOnClickListener(goToKalender);

        linearKelas = findViewById(R.id.linear_kelas);
        linearKelas.setOnClickListener(goToKelas);

        linearAbsen = findViewById(R.id.linear_absen);
        linearAbsen.setOnClickListener(goToAbsen);


    }

    View.OnClickListener goToProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent itentProfile = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(itentProfile);
        }
    };

    View.OnClickListener goToKalender = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent itentKalender = new Intent(MainActivity.this, KalenderActivity.class);
            startActivity(itentKalender);
        }
    };

    View.OnClickListener goToKelas = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent itentKelas = new Intent(MainActivity.this, KelasActivity.class);
            startActivity(itentKelas);
        }
    };

    View.OnClickListener goToAbsen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent itentAbsen = new Intent(MainActivity.this, AbsenActivity.class);
            startActivity(itentAbsen);
        }
    };
}
