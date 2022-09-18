package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class BerandaActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textPemasukan, textPengeluaran;
    ImageView pemasukan, pengeluaran, detail, setting;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        DB = new DBHelper(this);
        pemasukan = findViewById(R.id.imagePemasukan);
        pengeluaran = findViewById(R.id.imagePengeluaran);
        detail = findViewById(R.id.imageDetail);
        setting = findViewById(R.id.imageSetting);
        textPemasukan = findViewById(R.id.textPemasukan);
        textPengeluaran = findViewById(R.id.textPengeluaran);

        pemasukan.setOnClickListener(this);
        pengeluaran.setOnClickListener(this);
        detail.setOnClickListener(this);
        setting.setOnClickListener(this);
        totalMasukKeluar();
    }
    private void totalMasukKeluar() {
        NumberFormat formatRp = NumberFormat.getInstance(Locale.GERMANY);
        SQLiteDatabase db = DB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(nominal) AS total, " +
                "(SELECT SUM(nominal) FROM Datakas WHERE tipe_kas='Pemasukan') AS masuk, " +
                "(SELECT SUM(nominal) FROM Datakas WHERE tipe_kas='Pengeluaran') AS keluar " +
                "FROM Datakas", null);
        cursor.moveToFirst();

        int i;
        for (i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            textPemasukan.setText( "Rp. " + formatRp.format(cursor.getDouble(1)) );
            textPengeluaran.setText( "Rp. " + formatRp.format(cursor.getDouble(2)) );
        }

        refresh(1000);
    }

    private void refresh(int timer) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                totalMasukKeluar();
            }
        };
        handler.postDelayed(runnable, timer);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.imagePemasukan:
                i = new Intent(this, TambahPemasukanActivity.class);
                startActivity(i);
                break;

            case R.id.imagePengeluaran:
                i = new Intent(this, TambahPengeluaranActivity.class);
                startActivity(i);
                break;

            case R.id.imageDetail:
                i = new Intent(this, DetailCashFlowActivity.class);
                startActivity(i);
                break;

            case R.id.imageSetting:
                i = new Intent(this, PengaturanActivity.class);
                startActivity(i);
                break;
        }
    }
}
