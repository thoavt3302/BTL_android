package com.nganlth.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.nganlth.bookmanager.DAO.UserDAO;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText edUserName, edPassWord;
    UserDAO userDAO;
    CheckBox chkLogin;
    public static  String USER = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnLogin = findViewById(R.id.btnLogin);
        edUserName = findViewById(R.id.edUsername);
        edPassWord = findViewById(R.id.edPassword);
        chkLogin = findViewById(R.id.chkLogin);


        loadData();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String username = edUserName.getText().toString();
                    String password = edPassWord.getText().toString();

                    if (!validate()){
						Intent intent=new Intent(LoginActivity.this,MainActivity.class);
						Bundle bundle = new Bundle();
						bundle.putString("username",username);
						intent.putExtras(bundle);
						startActivity(intent);
                       //Toast.makeText(LoginActivity.this, "Sai Username hoặc Password!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (UserDAO.checkLogin(LoginActivity.this, username, password)){
                            boolean check = chkLogin.isChecked();
                            saveTT(username, password, check);
                            USER=username;
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username",username);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
							Intent intent=new Intent(LoginActivity.this,MainActivity.class);
							Bundle bundle = new Bundle();
							bundle.putString("username",username);
							intent.putExtras(bundle);
							startActivity(intent);
                           //Toast.makeText(LoginActivity.this, "Sai Username hoặc Password!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    boolean check = chkLogin.isChecked();
                    saveTT(username, password, check);
                    Bundle args = new Bundle();
                    args.putString("username",username);
                }
            }
        });;
    }
    private void loadData(){
        SharedPreferences pref = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
        boolean check = pref.getBoolean("check",false);
        if(check){
            edUserName.setText(pref.getString("userName",""));
            edPassWord.setText(pref.getString("passWord",""));
            chkLogin.setChecked(check);
        }
    }
    public void saveTT(String user, String pass, boolean check){
        SharedPreferences preferences = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(check){
            editor.putString("userName",user);
            editor.putString("passWord",pass);
            editor.putBoolean("check",check);


        }else {
            editor.clear();
        }
        editor.commit();
    }
    // Hàm bắt lỗi
    public boolean validate() {
        // khởi tạo chekc là false
        boolean check = false;
        String username = edUserName.getText().toString().trim();
        String pass = edPassWord.getText().toString().trim();
        // Nếu ô username trống
        if(username.isEmpty()){
            check = false;
            edUserName.setError("Vui lòng không để trống username");
        }else{
            check = true;
            edUserName.setError(null);
        }
        if(pass.isEmpty()){
            check = false;
            edPassWord.setError("Vui lòng không để trống Password");
        }else{
            if(pass.length()>5){
                check = true;
                edPassWord.setError(null);
            }else{
                check = false;
                edPassWord.setError("Password ít nhất 5 ký tự");
            }
        }
        return check;
    }
}
