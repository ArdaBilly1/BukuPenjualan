package com.example.bukupenjualan.libraries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "penjualan.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS penjualan(id integer PRIMARY KEY, namaPelanggan" +
                " VARCHAR, namaBarang VARCHAR, jumlah VARCHAR, harga VARCHAR, bayar VARCHAR," +
                "total integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS penjualan";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public Cursor executeRead(String sql){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }

    public void executeWrite(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }
}
