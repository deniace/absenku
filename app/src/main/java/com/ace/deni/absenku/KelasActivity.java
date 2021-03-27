package com.ace.deni.absenku;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ace.deni.absenku.adapter.KelasAdapter;
import com.ace.deni.absenku.model.Kelas;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class KelasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KelasAdapter adapter;
    private List<Kelas> list = new ArrayList<>();
    private FloatingActionButton floatButton;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);
        setTitle("Kelas");

        recyclerView = findViewById(R.id.recycler_kelas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        floatButton = findViewById(R.id.buttonAdd);
        floatButton.setOnClickListener(addListener);
        populateData();
    }

    private void populateData() {
        list = new Select().from(Kelas.class).execute();
        adapter = new KelasAdapter(list, new KelasAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Kelas item) {
                position = list.indexOf(item);
                Intent intent = new Intent(KelasActivity.this, TambahKelasActivity.class);
                intent.putExtra("ID_KLS", item.getId());
                startActivityForResult(intent, 1);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private FloatingActionButton.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            position = -1;
            Intent intent = new Intent(KelasActivity.this, TambahKelasActivity.class);
            intent.putExtra("ID_KLS", -1);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Long id = data.getLongExtra("ID_KLS", 0);
            String nama = data.getStringExtra("NAMA_KLS");
            Kelas kls = new Select().from(Kelas.class).where("id = ?", id).executeSingle();
            if(kls == null){
                if(nama != null || !nama.equals("")){
                    Toast.makeText(KelasActivity.this, "Kelas "+nama + " Terhapus", Toast.LENGTH_LONG).show();
                    populateData();
                }else {
                    Toast.makeText(KelasActivity.this, "Error !! id = "+id, Toast.LENGTH_LONG).show();
                }
            }else {
                if (position == -1){
                    list.add(kls);
                }else {
                    list.set(position, kls);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
