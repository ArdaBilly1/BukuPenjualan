package com.example.catatanpenjualan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.catatanpenjualan.entities.Penjualan;
import com.example.catatanpenjualan.models.PenjualanModel;

import java.util.*;

public class LihatDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Data
    private PenjualanModel mPenjualan;
    private ArrayList<Penjualan> allPenjualan;
    private ArrayList<String> daftarData;

    //Komponen
    private ListView lstDaftarData;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        this.initData();
        this.initComponents();
    }
    private void initData()
    {
        this.daftarData = new ArrayList<>();
        this.mPenjualan = new PenjualanModel(this);
        this.allPenjualan = this.mPenjualan.selectAll();
        for (Penjualan j:allPenjualan){
            this.daftarData.add(j.getNamaPelanggan());
        }
    }
    private void initComponents()
    {
        this.lstDaftarData = (ListView) this.findViewById(R.id.lstDaftarData);
        this.btnOk = (Button) this.findViewById(R.id.btnOk);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.daftarData);
        this.lstDaftarData.setAdapter(adapter);
        this.lstDaftarData.setOnItemClickListener(this);
    }
    public void button_onClick(View view)
    {
        Button b = (Button) view;
        if(b == this.btnOk){
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        int id = this.allPenjualan.get(i).getId();
        Intent intent = new Intent(this, DetailDataActivity.class);
        intent.putExtra("selectedPenjualanId", id);
        this.startActivity(intent);
    }
}
