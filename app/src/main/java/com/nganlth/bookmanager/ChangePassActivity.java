package com.nganlth.bookmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.nganlth.bookmanager.DAO.UserDAO;
import com.nganlth.bookmanager.Model.User;

public class ChangePassActivity extends AppCompatActivity {
    TextInputEditText edOldPass, edNewPass, edNewPassConfim;
    Button btnChange;
    TextView tv_home;

    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        edOldPass = findViewById(R.id.edOldPass);
        edNewPass = findViewById(R.id.edNewPass);
        edNewPassConfim = findViewById(R.id.edNewPassConfim);
        btnChange = findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDAO = new UserDAO(ChangePassActivity.this);
                        SharedPreferences pref = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
                        String strUserName = pref.getString("userName","");
                        String strPass = pref.getString("passWord","");
                        userDAO = new UserDAO(ChangePassActivity.this);
                        User user = new User(strUserName, edNewPass.getText().toString(), "",
                            "");
                        String newpass = edNewPass.getText().toString().trim();// loại bỏ cái chuỗi

                if((edOldPass.getText().toString().equals(strPass)) && (userDAO.changepass(strUserName,newpass))==true){

                    Toast.makeText(ChangePassActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ChangePassActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    public void changePassword(View v) {
//        SharedPreferences pref = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
//        String strUserName = pref.getString("username","");
//        String strPass = pref.getString("password","");
//        userDAO = new UserDAO(ChangePassActivity.this);
//        User user = new User(strUserName, edNewPass.getText().toString(), "",
//                "");
//        try {
//            if (validate() == true){
//                if (((edOldPass.getText().toString()).equals(strPass)) && (userDAO.changePasswordNguoiDung(user) > 0)) {
//                    finish();
//                    Toast.makeText(ChangePassActivity.this, "Lưu thành công",
//                            Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(ChangePassActivity.this, "Sai mật khẩu",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        } catch (Exception ex) {
//            Log.e("Error", ex.toString());
//        }
//    }
//    public boolean validate() {
//        return true;
//    }
}