package com.ace.deni.absenku;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

public class PilihAsenActivity extends AppCompatActivity {

    private LinearLayout linearPilihTanggal;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_asen);
        setTitle("Pilih Absen Kelas");

        linearPilihTanggal = findViewById(R.id.linear_pilih_tanggal);
        linearPilihTanggal.setOnClickListener(PilihTanggal);
    }


    private View.OnClickListener PilihTanggal = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            datePickerDialog = new DatePickerDialog(PilihAsenActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                }
            },mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    };
}
