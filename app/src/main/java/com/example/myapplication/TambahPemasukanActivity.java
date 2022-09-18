package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TambahPemasukanActivity extends AppCompatActivity implements View.OnClickListener{

    Button simpanPemasukan, kembaliPemasukan;
    EditText txtTanggal,nominal, keterangan;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    String tipeKas;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pemasukan);

        DB = new DBHelper(this);

        txtTanggal = findViewById(R.id.editTanggalPengeluaran);
        nominal = findViewById(R.id.editNominalPengeluaran);
        keterangan = findViewById(R.id.editKeteranganPengeluaran);
        tipeKas = "Pemasukan";
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        simpanPemasukan = findViewById(R.id.btnSimpanPengaturan);
        kembaliPemasukan = findViewById(R.id.btnKembaliPengaturan);

        txtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        simpanPemasukan.setOnClickListener(this);
        kembaliPemasukan.setOnClickListener(this);
    }

        private void showDateDialog() {
            Calendar calendar = Calendar.getInstance();
            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, month, dayOfMonth);
                    txtTanggal.setText(dateFormatter.format(newDate.getTime()));
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnSimpanPengaturan:
                if (txtTanggal.getText().toString().equals("") || nominal.getText().toString().equals("") || keterangan.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = DB.getWritableDatabase();
                    db.execSQL("INSERT INTO Datakas(tipe_kas, nominal, keterangan, tanggal) VALUES('" + tipeKas + "','" + nominal.getText().toString() + "','" + keterangan.getText().toString() + "','" + txtTanggal.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;

            case R.id.btnKembaliPengaturan:
                i = new Intent(this, BerandaActivity.class);
                startActivity(i);
                break;
        }
    }
}