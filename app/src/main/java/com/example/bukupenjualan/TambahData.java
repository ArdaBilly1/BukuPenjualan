package com.example.bukupenjualan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bukupenjualan.entities.Penjualan;
import com.example.bukupenjualan.models.PenjualanModel;


public class TambahData extends AppCompatActivity {
    //Data
    private PenjualanModel mPenjualan;
    private int t;
    private int jB;
    private int hrg;

    //Komponen
    private EditText txtNamaPelanggan;
    private EditText txtNamaBarang;
    private EditText txtJumlah;
    private EditText txtHarga;
    private EditText txtTotal;
    private Button btnProses;
    private Button btnSimpan;
    private Button btnBatal;

    //Boolean
    private boolean mProses = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        this.initData();
        this.initComponents();


    }

    private void initData(){
        this.mPenjualan = new PenjualanModel(this);
    }

    private void initComponents(){
        this.txtNamaPelanggan = (EditText) this.findViewById(R.id.txtNamaPelanggan);
        this.txtNamaBarang = (EditText) this.findViewById(R.id.txtNamaBarang);
        this.txtJumlah = (EditText) this.findViewById(R.id.txtJumlah);
        this.txtHarga = (EditText) this.findViewById(R.id.txtHarga);
        this.txtTotal = (EditText) this.findViewById(R.id.txtTotal);
        this.btnProses = (Button) this.findViewById(R.id.btnProses);
        this.btnSimpan = (Button) this.findViewById(R.id.btnSimpan);
        this.btnBatal = (Button) this.findViewById(R.id.btnBatal);

        btnProses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    mProses = true;
                    txtTotal.setText(null);
                    proses();
            }
        });
    }

    public void button_onClick(View view){
        Button b = (Button) view;
        if (b == this.btnSimpan){
            if (txtNamaPelanggan.getText().toString().equals("") || txtNamaBarang.getText().toString().equals("")
            || txtHarga.getText().toString().equals("") || txtJumlah.getText().toString().equals("")
            || txtTotal.getText().toString().equals("")){
                Toast.makeText(this, "Gagal Simpan Data. Isi Dulu", Toast.LENGTH_SHORT).show();
            } else {
                this.tambahData();
            }
        }

        else if (b == this.btnBatal){
            this.finish();
        }
    }

    private void tambahData(){
        String namaPelanggan = this.txtNamaPelanggan.getText().toString();
        String namaBarang = this.txtNamaBarang.getText().toString();
        String jumlah = this.txtJumlah.getText().toString();
        String harga = this.txtHarga.getText().toString();
        String total = this.txtTotal.getText().toString();

        Penjualan penjualanbaru = new Penjualan();
        penjualanbaru.setNamaPelanggan(namaPelanggan);
        penjualanbaru.setNamaBarang(namaBarang);
        penjualanbaru.setJumlah(jumlah);
        penjualanbaru.setHarga(harga);
        penjualanbaru.setTotal(total);

        this.mPenjualan.insert(penjualanbaru);

        Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

        this.btnBatal.setText("Kembali");
    }

    public void proses(){
        String jumlah = this.txtJumlah.getText().toString().trim();
        String harga = this.txtHarga.getText().toString().trim();

        this.jB = Integer.valueOf(jumlah);
        this.hrg = Integer.valueOf(harga);
        this.t = (this.jB * this.hrg);
        this.txtTotal.setText(""+this.t);
    }
}
