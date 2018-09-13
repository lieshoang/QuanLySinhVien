package com.example.admin.quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Làm việc với database
 * cho phep tao, sửa, xóa, database
 */
public class DatabaseManager {
    //Bước 1: khai báo thông tin database: tên, phiên bản, tên bảng...
    private final String DB_NAME = "hoso.db";
    private final String TB_NAME_LOP = "Lop";
    private final String TB_NAME_SV = "SinhVien";
    private final int DB_VERSION = 1; //version cua database
    private SQLiteDatabase database;

    //Bước 2: xây dựng lớp SQLliteOpenHelper để quản lý việc upgradle db
    private class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Tạo ra các bảng
            String lop = "CREATE TABLE IF NOT EXISTS Lop" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT,maLop TEXT,tenLop TEXT)";
            String sv = "CREATE TABLE IF NOT EXISTS SinhVien" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT,tenSV TEXT,diaChi TEXT, tenLop TEXT)";
            db.execSQL(lop);
            db.execSQL(sv);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String xoaLop = "DROP TABLE IF EXISTS Lop";
            String xoaSV = "DROP TABLE IF EXISTS SinhVien";
            db.execSQL(xoaLop);
            db.execSQL(xoaSV);

        }
    }

    //Bước 3: xây dựng phương thức cơ bản để làm việc với database
    //INSERT
    public void insertLop(String maLop, String tenLop) {
        ContentValues values = new ContentValues();
        values.put("maLop", maLop);
        values.put("tenLop", tenLop);
        database.insertOrThrow(TB_NAME_LOP, null, values);

    }

    public void insertSV(String tenSV, String diaChi, String tenLop) {
        ContentValues values = new ContentValues();
        values.put("tenSV", tenSV);
        values.put("diaChi", diaChi);
        values.put("tenLop", tenLop);
        database.insertOrThrow(TB_NAME_SV, null, values);
    }

    //UPDATE
    public void updateLop(int _id, String maLop, String tenLop) {
        ContentValues values = new ContentValues();
        values.put("maLop", maLop);
        values.put("tenLop", tenLop);
        database.update(TB_NAME_LOP, values, "_id", null);
    }

    public void updateSV(int _id, String tenSV, String diaChi, String tenLop) {
        ContentValues values = new ContentValues();
        values.put("tenSV", tenSV);
        values.put("diaChi", diaChi);
        values.put("tenLop", tenLop);
        database.update(TB_NAME_SV, values, "_id", null);
    }

    //DELETE
    public void deleteLop(int _id) {
        database.delete(TB_NAME_LOP, "_id", null);
    }

    public void deleteSV(int _id) {
        database.delete(TB_NAME_SV, "_id", null);
    }

    //SELECT

    public Cursor selectLop() {
        return database.query(TB_NAME_LOP, null, null, null, null, null, null);
    }

    public Cursor selectSV() {
        return database.query(TB_NAME_SV, null, null, null, null, null, null);
    }

    //Bước 4: Xây dựng phương thức khởi tạo của lớp database manager
    public DatabaseManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }
}
