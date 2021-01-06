package com.vn.itplus.myrelish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleLoginActivity extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView loginMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginMessage = findViewById(R.id.login_message);
    }

    public void login(View view){
        // send login request to server
        if ("vqd9x".equals(loginEmail.getText().toString()) && "123456".equals(loginPassword.getText().toString())){
            onLoginSuccess(1);
        }else{
            onLoginFailed();
        }
    }
    public void dontLogin(View view){
        finish();
    }

    private void onLoginSuccess(int userId){
        Toast.makeText(this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userId",userId);
        editor.apply();
        finish();
    }

    private void onLoginFailed(){
        Toast.makeText(this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        loginMessage.setTextColor(getResources().getColor(R.color.colorError));
        loginMessage.setText("Sai tên đăng nhập hoặc mật khẩu");
    }
}
