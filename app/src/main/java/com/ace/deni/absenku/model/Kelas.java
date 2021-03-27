package com.ace.deni.absenku.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Deni Supriyatna on 10/01/2019.
 */
@Table(name = "Kelas")
public class Kelas extends Model {

    @Column(name = "namaKelas")
    private String namaKelas;

    @Column(name = "descKelas")
    private String descKelas;

    public Kelas(){

    }

    public Kelas (String namaKelas, String descKelas){
        this.namaKelas = namaKelas;
        this.descKelas = descKelas;

    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getDescKelas() {
        return descKelas;
    }

    public void setDescKelas(String descKelas) {
        this.descKelas = descKelas;
    }

}
