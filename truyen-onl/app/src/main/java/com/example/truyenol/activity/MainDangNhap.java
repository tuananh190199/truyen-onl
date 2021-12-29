package com.example.truyenol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;


public class MainDangNhap extends AppCompatActivity {
    //tạo biến cho màn đăng nhập
    EditText edituser,editpass;
    Button dangnhap,dangky;
    //tạo đối tượng cho database
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        Login();
        //đối tượng database
        databaseHandler = new DatabaseHandler(this);
        //tạo sự kiện click button chuyển sang trang đăng ký vs intent
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán cho các biến có giá trị nhập vào từ edit text
                String tentaikhoan = edituser.getText().toString();
                String matkhau = editpass.getText().toString();
                Cursor cursor = databaseHandler.getData();
                while (cursor.moveToNext()){
                    String datausername = cursor.getString(1);
                    String datapassword = cursor.getString(2);

                    //nếu tài khoản và mật khẩu nhập tư bàn phím khớp với ở database
                    if (datausername.equals(tentaikhoan)&&datapassword.equals(matkhau)){
                        String phanquyen = cursor.getString(6);
                        String linkAva = cursor.getString(5);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(4);
                        String fullname = cursor.getString(3);
                        Intent intent = new Intent(MainDangNhap.this, HomeActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("User",getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("linkAva",linkAva);
                        edit.putInt("idUser",idd);
                        edit.putString("fullname",fullname);
                        edit.putString("email",email);
                        edit.putString("position",phanquyen);
                        edit.commit();
                        startActivity(intent);
                        Log.d("Sai thông tin đăng nhập","Thử lại");
                        if (tentaikhoan.equals("") || matkhau.equals("")){
                            Log.e("Thiếu thông tin", "Thất bại");

                        }

                    }
                }
                cursor.moveToFirst();
                cursor.close();
            }
        });
    }
    private void Login(){
        edituser = findViewById(R.id.edittextuser);
        editpass = findViewById(R.id.edittextpass);
        dangnhap = findViewById(R.id.btndangnhap);
        dangky = findViewById(R.id.btndangky);
    }
}