package com.example.bukupenjualan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLihatData;
    private Button btnTambahData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();
    }

    private void initComponents(){
        this.btnLihatData = (Button) this.findViewById(R.id.btnLihatData);
        this.btnTambahData = (Button) this.findViewById(R.id.btnTambahData);
    }

    public void button_onClick(View view){
        Button b = (Button) view;

        if (b == this.btnLihatData){
            this.openLihatDataActivity();
        } else if (b == this.btnTambahData){
            this.openTambahData();
        }
    }

    private void openLihatDataActivity(){
        Intent i = new Intent(this, LihatDataActivity.class);
        this.startActivity(i);
    }

    private void openTambahData(){
        Intent i = new Intent(this, TambahData.class);
        this.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
