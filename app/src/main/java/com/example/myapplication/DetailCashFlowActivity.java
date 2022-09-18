package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailCashFlowActivity extends AppCompatActivity implements View.OnClickListener {

    public static String id_kas;
    DBHelper DB;
    Cursor cursor;
    String queryGetAllKas;
    ArrayList<DataKas> kasList;
    RecyclerView recyclerView;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash_flow);
        id_kas = "";
        queryGetAllKas = "";
        DB   = new DBHelper(this);
        recyclerView = findViewById(R.id.rvKas);
        imageViewBack = findViewById(R.id.imageViewBack);
        kasList = new ArrayList<>();
        imageViewBack.setOnClickListener(this);
        KasAdapter();
        setAdapter();
    }

    private void KasAdapter() {
        queryGetAllKas  = "SELECT id_kas, nominal, tipe_kas, keterangan, tanggal " + "FROM Datakas ORDER BY id_kas DESC";
        SQLiteDatabase db = DB.getReadableDatabase();
        cursor = db.rawQuery(queryGetAllKas, null);
        cursor.moveToFirst();

        int i;
        for (i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            kasList.add(new DataKas(cursor.getString(0), cursor.getString(2), cursor.getString(1), cursor.getString(3),cursor.getString(4)));
        }
        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Tidak ada Kas Pemasukan maupun Pengeluaran", Toast.LENGTH_LONG).show();
        }
    }

    private void setAdapter(){
        DataKasAdapter adapter = new DataKasAdapter(kasList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.imageViewBack:
                i = new Intent(this, BerandaActivity.class);
                startActivity(i);
                break;
        }
    }

}