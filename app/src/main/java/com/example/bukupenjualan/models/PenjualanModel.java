package com.example.bukupenjualan.models;

import android.content.Context;
import android.database.Cursor;

import com.example.bukupenjualan.entities.Penjualan;
import com.example.bukupenjualan.libraries.DbHelper;

import java.util.ArrayList;

public class PenjualanModel {
    private Context context;
    private DbHelper db;

    public PenjualanModel(Context context){
        this.context = context;
        this.db = new DbHelper(this.context);
    }

    public ArrayList<Penjualan>selectAll(){
        String sql = "SELECT * FROM penjualan";
        ArrayList<Penjualan> semuaPenjualan = new ArrayList<>();
        Cursor cursor = this.db.executeRead(sql);

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do {
                int id = cursor.getInt(0);
                String namaPelanggan = cursor.getString(1);
                String namaBarang = cursor.getString(2);
                String jumlah = cursor.getString(3);
                String harga = cursor.getString(4);
                String total = cursor.getString(5);

                Penjualan j = new Penjualan();

                j.setId(id);
                j.setNamaPelanggan(namaPelanggan);
                j.setNamaBarang(namaBarang);
                j.setJumlah(jumlah);
                j.setHarga(harga);
                j.setTotal(total);

                semuaPenjualan.add(j);
            }
            while (cursor.moveToNext());
        }

        return semuaPenjualan;
    }

    public void insert(Penjualan j){
        String namaPelanggan = j.getNamaPelanggan();
        String namaBarang = j.getNamaBarang();
        String jumlah = j.getJumlah();
        String harga = j.getHarga();
        String total = j.getTotal();

        String sql = "INSERT INTO penjualan(namaPelanggan, namaBarang, jumlah, harga, total)" +
                " VALUES ('"+namaPelanggan+"','"+namaBarang+"','"+jumlah+"','"+harga+"','"+total+"')";
        this.db.executeWrite(sql);
    }

    public void update(Penjualan j){
        if (j.getId() < 0){
            return;
        }

        int id = j.getId();
        String namaPelanggan = j.getNamaPelanggan();
        String namaBarang = j.getNamaBarang();
        String jumlah = j.getJumlah();
        String harga = j.getHarga();
        String total = j.getTotal();

        String sql = "UPDATE penjualan SET namaPelanggan = '"+namaPelanggan+"', namaBarang = '"+namaBarang+"', " +
                "jumlah = '"+jumlah+"', harga = '"+harga+"', total = '"+total+"' WHERE id = '"+id+"'";
        this.db.executeWrite(sql);
    }

    public Penjualan selectOne(int id){
        String sql = "SELECT * FROM penjualan WHERE id = '"+id+"'";
        Cursor cursor = this.db.executeRead(sql);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            Penjualan j = new Penjualan();

            j.setId(cursor.getInt(0));
            j.setNamaPelanggan(cursor.getString(1));
            j.setNamaBarang(cursor.getString(2));
            j.setJumlah(cursor.getString(3));
            j.setHarga(cursor.getString(4));
            j.setTotal(cursor.getString(5));

            return j;
        }
        return null;
    }

    public void delete(Penjualan j){
        if (j.getId() < 0){
            return;
        }

        String sql = "DELETE FROM penjualan WHERE id = '"+j.getId()+"'";
        this.db.executeWrite(sql);
    }
}
