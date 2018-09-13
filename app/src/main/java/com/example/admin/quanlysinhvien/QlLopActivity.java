package com.example.admin.quanlysinhvien;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class QlLopActivity extends AppCompatActivity {
    private EditText edMaLop;
    private EditText edTenLop;

    private DatabaseManager databaseManager;

    private ListView lvLop;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;//kết nối từng hàng tới từng item.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_lop);
        edMaLop = findViewById(R.id.edMaLop);
        edTenLop = findViewById(R.id.edTenLop);
        lvLop = findViewById(R.id.lvLop);

        databaseManager = new DatabaseManager(this);
        hienThiLop();
    }

    public void themLop(View view) {
        // lay thong tin nhap vao
        String maLop = edMaLop.getText().toString().trim();
        String tenLop = edTenLop.getText().toString().trim();

        edTenLop.setText("");
        edMaLop.setText("");
        //tim kiem thong tin
        if (maLop.equals("") || tenLop.equals("")) {
            Toast.makeText(this, "Nhập Thông Tin", Toast.LENGTH_SHORT).show();
        } else {
            //TODO insert to database
            databaseManager.insertLop(maLop, tenLop);
            //TODO update to database
            hienThiLop();
        }


    }

    public void huyThemLop(View view) {
        edTenLop.setText("");
        edMaLop.setText("");
    }

    public void hienThiLop() {
        //lấy các lớp trong bảng lớp và cập nhật cho list view
        cursor = databaseManager.selectLop();
        //cập nhật listview
        if (cursor != null) {
            String[] columns = {"_id", "maLop", "tenLop"};
            //kb id cac textview cua item
            int[] ids = {R.id.tvID, R.id.tvMaLop, R.id.tvTenLop};

            adapter = new SimpleCursorAdapter(this,R.layout.item_lop,cursor,columns,ids);
            lvLop.setAdapter(adapter);

        }
    }
}
