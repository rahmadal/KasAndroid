package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PengaturanActivity extends AppCompatActivity implements View.OnClickListener {
    EditText passwordBaru, passwordLama;
    Button btnSimpan, kembali;
    DBHelper DB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        passwordLama = findViewById(R.id.editPasswordLama);
        passwordBaru = findViewById(R.id.editPasswordBaru);
        btnSimpan = findViewById(R.id.btnSimpanPengaturan);
        kembali = findViewById(R.id.btnKembaliPengaturan);

        DB = new DBHelper(this);

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnSimpanPengaturan:
                String strPasswordLama="";
                String queryGetPasswordOld  = "SELECT password " + "FROM users";
                SQLiteDatabase db = DB.getReadableDatabase();
                cursor = db.rawQuery(queryGetPasswordOld, null);
                cursor.moveToFirst();

                int i;
                for (i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    strPasswordLama = cursor.getString(0);
                }
                if(!passwordLama.getText().toString().equals(strPasswordLama)){
                    Toast.makeText(getApplicationContext(), "Password Lama Salah", Toast.LENGTH_LONG).show();
                }else{
                    db.execSQL("UPDATE users SET password="+"'"+passwordBaru.getText().toString()+"'"+" WHERE id_user=1");
                    Toast.makeText(getApplicationContext(), "Password Berhasil Diubah", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                break;
                case R.id.btnKembaliPengaturan:
                intent = new Intent(this, BerandaActivity.class);
                startActivity(intent);
                break;
        }
    }
}