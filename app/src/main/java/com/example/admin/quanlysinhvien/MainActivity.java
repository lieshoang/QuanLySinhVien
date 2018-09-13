package com.example.admin.quanlysinhvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quanLyLop(View view) {
        Intent intent = new Intent(this,QlLopActivity.class);
        startActivity(intent);
    }

    public void quanLySinhVien(View view) {
        Intent intent = new Intent(this,QuanLySinhVienActivity.class);
        startActivity(intent);
    }
}
