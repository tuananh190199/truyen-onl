package com.example.truyenol.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyenol.R;
import com.example.truyenol.database.DatabaseHandler;

import com.example.truyenol.model.User;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;




public class MainDangKy extends AppCompatActivity {
    EditText editDangkyTK,editDangkyMK,editDangkyFull,editDangkyEmail;
    Button btndangky,btntrolai,btnanh;
    ImageView imgPhoto;
    TextView linkAva;
    DatabaseHandler databaseHandler;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        Signin();
        databaseHandler = new DatabaseHandler(this);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editDangkyTK.getText().toString();
                String password = editDangkyMK.getText().toString();
                String email = editDangkyEmail.getText().toString();
                String fullname = editDangkyFull.getText().toString();
                String avatar = linkAva.getText().toString() ;
                User user1= CreateUser();
                if (username.equals("") || password.equals("") || email.equals("") || fullname.equals("")||avatar.equals("") ){
                    Log.e("Thông báo","Chưa nhập đầy đủ thông tin");
                }
                else {
                    databaseHandler.addTaikhoan(user1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();

                }
            }
        });
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chonanh = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(chonanh, REQUEST_CODE_CAMERA);
            }
        });
        btnanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chonimg= new Intent(Intent.ACTION_PICK);
                chonimg.setType("image/*");
                doSetLinkAva();
                startActivityForResult(chonimg,REQUEST_CODE_FOLDER);
            }
        });
        btntrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trolai = new Intent( MainDangKy.this,MainDangNhap.class);
                startActivity(trolai);
            }
        });

    }

    private User CreateUser(){
        User tk = new User();
        String username = editDangkyTK.getText().toString();
        String password = editDangkyMK.getText().toString();
        String fullname = editDangkyFull.getText().toString();
        String avatar = linkAva.getText().toString() ;
        String email = editDangkyEmail.getText().toString();
        String position = "Admin";
        tk.setUsername(username);
        tk.setPassword(password);
        tk.setFullName(fullname);
        tk.setLinkAva(avatar);
        tk.setEmail(email);
        tk.setPosition(position);
        return tk;

    }
    private void doSetLinkAva(){
//        Intent setlink = new Intent(Intent.ACTION_GET_CONTENT);
//        setlink.setType("*/*");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent,1);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA&& resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }
        if(requestCode==1&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_READ_URI_PERMISSION);
            linkAva.setText(uri.toString());
        }
        if (requestCode == REQUEST_CODE_FOLDER&&resultCode == RESULT_OK&&data!=null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgPhoto.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Signin(){

        editDangkyTK = findViewById(R.id.edituser);
        editDangkyMK = findViewById(R.id.editpass);
        editDangkyEmail = findViewById(R.id.editemail);
        imgPhoto = findViewById(R.id.choseimage);
        btnanh = findViewById(R.id.btnchonanh);
        btndangky = findViewById(R.id.dangky);
        btntrolai = findViewById(R.id.trolai);
        linkAva = findViewById(R.id.linkAvaTxt);
        editDangkyFull = findViewById(R.id.editfullname);

    }

}