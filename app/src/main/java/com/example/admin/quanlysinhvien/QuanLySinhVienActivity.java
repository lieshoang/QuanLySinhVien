package com.example.admin.quanlysinhvien;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class QuanLySinhVienActivity extends Activity {
    private TextView edTenSinhVien;
    private TextView edDiaChi;
    private Button btThemSV;
    private Button btHuySV;
    private ListView lvSinhVien;
    private TextView hihi;

    private Spinner spinLop;
    private Cursor cursorLop;
    private Cursor cursorSV;
    private SimpleCursorAdapter adapterLop;
    private SimpleCursorAdapter adapterSV;

    private DatabaseManager databaseManager;
    private String tenLop;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sinh_vien);

        edDiaChi = findViewById(R.id.edDiaChi);
        edTenSinhVien = findViewById(R.id.edTenSinhVien);
        spinLop = findViewById(R.id.spTenLop);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        databaseManager = new DatabaseManager(this);
        HienThiLop();

        HienThiSV();

        spinLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) parent.getItemAtPosition(position);
                tenLop = c.getString(2);
                Toast.makeText(QuanLySinhVienActivity.this, tenLop, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void themSinhvien(View view) {
        String tenSV = edTenSinhVien.getText().toString().trim();
        String diaChi = edDiaChi.getText().toString().trim();
        if (tenSV.equals("") || diaChi.equals("")) {
            Toast.makeText(this, "Nhập thông tin", Toast.LENGTH_LONG).show();
        } else {
            databaseManager.insertSV(tenSV, diaChi, tenLop);
            //cap nhat lai danh sach sinh vien
            HienThiSV();

        }

    }

    public void huyTinhvien(View view) {
    }

    public void HienThiLop() {
        cursorLop = databaseManager.selectLop();
        if (cursorLop != null) {
            String[] columns = {"tenLop"};
            //id cua textview hien thi cho item cua Spinner
            int[] ids = {android.R.id.text1};
            adapterLop = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursorLop, columns, ids);
            spinLop.setAdapter(adapterLop);
        }

    }

    public void HienThiSV() {
        cursorSV = databaseManager.selectSV();
        if (cursorSV != null) {
            String[] columns = {"_id", "tenSV", "diaChi", "tenLop"};
            int[] ids = {R.id.tvID, R.id.tvtenSV, R.id.tvDiaChi, R.id.tvLopSV};
            adapterSV = new SimpleCursorAdapter(this, R.layout.item_sv, cursorSV, columns, ids);
            lvSinhVien.setAdapter(adapterSV);
        }
    }

}
