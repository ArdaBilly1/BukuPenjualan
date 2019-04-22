package com.example.bukupenjualan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bukupenjualan.entities.Penjualan;
import com.example.bukupenjualan.models.PenjualanModel;

public class DetailDataActivity extends AppCompatActivity {
    //Data
    private PenjualanModel mPenjualan;
    private Penjualan selectedPenjualan;

    //Komponen
    private EditText txtNamaPelanggan;
    private EditText txtNamaBarang;
    private EditText txtJumlah;
    private EditText txtHarga;
    private EditText txtTotal;
    private Button btnUbah;
    private Button btnHapus;
    private Button btnKembali;
    private String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        this.initData();
        this.initComponents();
    }

    private void initData(){
        this.mPenjualan = new PenjualanModel(this);
        int selectedPenjualanId = this.getIntent().getIntExtra("selectedPenjualanId", -1);
        this.selectedPenjualan = this.mPenjualan.selectOne(selectedPenjualanId);
    }

    private void initComponents(){
        this.txtNamaPelanggan = (EditText)this.findViewById(R.id.txtNamaPelanggan);
        this.txtNamaBarang = (EditText)this.findViewById(R.id.txtNamaBarang);
        this.txtJumlah = (EditText)this.findViewById(R.id.txtJumlah);
        this.txtHarga = (EditText)this.findViewById(R.id.txtHarga);
        this.txtTotal = (EditText)this.findViewById(R.id.txtTotal);
        this.btnUbah = (Button)this.findViewById(R.id.btnUbah);
        this.btnHapus = (Button)this.findViewById(R.id.btnHapus);
        this.btnKembali = (Button)this.findViewById(R.id.btnKembali);

        this.txtNamaPelanggan.setText(this.selectedPenjualan.getNamaPelanggan());
        this.txtNamaBarang.setText(this.selectedPenjualan.getNamaBarang());
        this.txtJumlah.setText(this.selectedPenjualan.getJumlah());
        this.txtHarga.setText(this.selectedPenjualan.getHarga());
        this.txtTotal.setText(this.selectedPenjualan.getTotal());
    }

    public void button_onClick(View view){
        Button b = (Button) view;

        if (b == btnUbah){
            this.ubahData();
        }
        else if (b == btnHapus){
            this.hapusData();
        }
        else if (b == btnKembali){
            Intent i = new Intent(this, LihatDataActivity.class);
            this.startActivity(i);
            this.finish();
        }
    }

    private void ubahData(){
        String namaPelanggan = this.txtNamaPelanggan.getText().toString();
        String namaBarang = this.txtNamaBarang.getText().toString();
        String jumlah = this.txtJumlah.getText().toString();
        String harga = this.txtHarga.getText().toString();
        String total = this.txtTotal.getText().toString();

        this.selectedPenjualan.setNamaPelanggan(namaPelanggan);
        this.selectedPenjualan.setNamaBarang(namaBarang);
        this.selectedPenjualan.setJumlah(jumlah);
        this.selectedPenjualan.setHarga(harga);
        this.selectedPenjualan.setTotal(total);

        this.mPenjualan.update(this.selectedPenjualan);

        this.resetFields("Data Berhasil di Update", false);
    }

    private void hapusData(){
        this.mPenjualan.delete(this.selectedPenjualan);
        this.resetFields("Data dihapus", true);
        this.btnHapus.setEnabled(false);
    }

    private void resetFields(String pesan, boolean clear){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();

        if (clear){
            this.txtNamaPelanggan.setText("");
            this.txtNamaBarang.setText("");
            this.txtJumlah.setText("");
            this.txtHarga.setText("");
            this.txtTotal.setText("");
        }
    }
}
