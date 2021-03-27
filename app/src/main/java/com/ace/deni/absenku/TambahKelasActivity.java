package com.ace.deni.absenku;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.RestrictTo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ace.deni.absenku.adapter.KelasAdapter;
import com.ace.deni.absenku.model.Kelas;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TambahKelasActivity extends AppCompatActivity {

    private TextInputEditText textIdKelasTambah,textDescKelasTambah;
    private long idKls;
    private boolean isNew = false;
    private Kelas kls = null;
    private Button buttonDelete;

    private RecyclerView recyclerView;
    private FloatingActionButton floatButton;
    private int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
        setTitle("Tambah Kelas");

        textIdKelasTambah = findViewById(R.id.text_id_kelas_tambah);
        textDescKelasTambah = findViewById(R.id.text_desc_kelas_tambah);
        buttonDelete = findViewById(R.id.button_delete);
        recyclerView = findViewById(R.id.recycler_kelas_peserta);

        floatButton = findViewById(R.id.button_add_peserta);
        floatButton.setOnClickListener(addPesertaListener);

        Intent intent = getIntent();
        idKls = intent.getLongExtra("ID_KLS", -1);
        if(idKls> -1){
            isNew = false;
            populateControls();
            buttonDelete.setVisibility(View.VISIBLE);
            floatButton.show();
        }else{
            isNew = true;
            kls = new Kelas();
            buttonDelete.setVisibility(View.GONE);
            floatButton.hide();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            Intent parent = new Intent();
            setResult(1, parent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateControls() {
        kls = new Select().from(Kelas.class).where("id = ?", idKls).executeSingle();
        if (kls != null){
            textIdKelasTambah.setText(kls.getNamaKelas());
            textDescKelasTambah.setText(kls.getDescKelas());
        }
    }

    public void save(View view){
        // validatiion
        String namaKelas = "", deskripsiKelas = "";
        try {
            namaKelas = textIdKelasTambah.getText().toString();
            deskripsiKelas = textDescKelasTambah.getText().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(namaKelas == null || namaKelas.equals("") || namaKelas.length() == 0){
            Toast.makeText(this, "Nama Kelas Harus DI isi", Toast.LENGTH_LONG).show();
        }else if(deskripsiKelas == null || deskripsiKelas.equals("") || deskripsiKelas.length() == 0){
            Toast.makeText(this, "Deskripsi harus di isi", Toast.LENGTH_LONG).show();
        }else{
            kls.setNamaKelas(namaKelas);
            kls.setDescKelas(deskripsiKelas);

            long id = kls.save();
            if(isNew){
                //update saldo
                kls.setNamaKelas(namaKelas);
                kls.setDescKelas(deskripsiKelas);
                kls.save();
            }

            Intent intent = new Intent();
            intent.putExtra("ID_KLS", id);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void delete(View view){
        String namaKelas;
        if (kls != null){
            namaKelas = textIdKelasTambah.getText().toString();
            new Delete().from(Kelas.class).where("namaKelas = ?", namaKelas).execute();
            Intent intent = new Intent();
            intent.putExtra("ID_KLS", kls.getId());
            intent.putExtra("NAMA_KLS", kls.getNamaKelas());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private FloatingActionButton.OnClickListener addPesertaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            position = -1;
            Intent intent = new Intent(TambahKelasActivity.this, TambahPesertaActivity.class);
            intent.putExtra("ID_KLS", -1);
            startActivityForResult(intent, 1);
        }
    };
}
