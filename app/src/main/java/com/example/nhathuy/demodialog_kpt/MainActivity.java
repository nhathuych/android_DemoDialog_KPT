package com.example.nhathuy.demodialog_kpt;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUser, edtPassword;
    Button btnDangKy, btnDangNhap, btnThoat;

    String ten, matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
                builder.setTitle("Xác nhận thoát");
                builder.setMessage("Bạn có chắc là muốn thoát");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setCancelable(false);    // khi người dùng click ra ngoài dialog thì sẽ ko tắt

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();    // thoát khỏi app
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ko cần làm gì
                    }
                });

                builder.show();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Xử lý đăng ký");
                dialog.setCancelable(false);    // khi người dùng click ra ngoài dialog thì sẽ ko tắt
                dialog.setContentView(R.layout.customdialog);

                final EditText edtTaiKhoan = (EditText) dialog.findViewById(R.id.edtTaiKhoan);
                final EditText edtMatKhau = (EditText) dialog.findViewById(R.id.edtMatKhau);
                Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
                Button btnXacNhan = (Button) dialog.findViewById(R.id.btnXacNhan);

                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ten = edtTaiKhoan.getText().toString().trim();
                        matkhau = edtMatKhau.getText().toString().trim();

                        edtUser.setText(ten);
                        edtPassword.setText(matkhau);

                        dialog.cancel();
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtUser.getText().toString().isEmpty() && !edtPassword.getText().toString().isEmpty()) {
                    if(edtUser.getText().toString().equals(ten) && edtPassword.getText().toString().equals(matkhau)) {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công ^_^", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void addControls() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnThoat = (Button) findViewById(R.id.btnThoat);
    }
}
