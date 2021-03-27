package com.ace.deni.absenku;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ace.deni.absenku.adapter.KelasAdapter;
import com.ace.deni.absenku.model.Kelas;

import java.util.ArrayList;
import java.util.List;

public class AbsenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private FloatingActionButton floatButton;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        setTitle("Absen");

        recyclerView = findViewById(R.id.recycler_absen);


        floatButton = findViewById(R.id.buttonAddAbsen);
        floatButton.setOnClickListener(addAbsenListener);

    }

    private FloatingActionButton.OnClickListener addAbsenListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            position = -1;
            Intent intent = new Intent(AbsenActivity.this, PilihAsenActivity.class);
            intent.putExtra("ID_ABSEN", -1);
            startActivityForResult(intent, 1);
        }
    };
}
