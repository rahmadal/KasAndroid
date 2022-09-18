package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "kas.db";

    public DBHelper(Context context) {
        super(context, "kas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Datakas(id_kas integer primary key autoincrement not null, nominal DOUBLE, tipe_kas TEXT, keterangan TEXT, tanggal DATE)");
        db.execSQL("create table users(id_user integer primary key autoincrement not null, username TEXT, password TEXT)");
        db.execSQL("insert into users values(1, 'user', 'user')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists Datakas");
    }

    public boolean cekLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
