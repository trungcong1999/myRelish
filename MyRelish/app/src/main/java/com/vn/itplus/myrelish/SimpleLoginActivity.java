package com.vn.itplus.myrelish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimpleLoginActivity extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPassword;
    private TextView loginMessage;
    private ProgressDialog pDialog;

    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginMessage = findViewById(R.id.login_message);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang xử lý đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    public void login(View view){
        // send login request to server
        final String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();

        String url = getResources().getString(R.string.server_url)+"/user/api/login";

        pDialog.show();

        StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try{
                            JSONObject jsonResult = new JSONObject(response);
                            if (jsonResult.has("id")){
                                onLoginSuccess(jsonResult.getInt("id"));
                            }else{
//                                Toast.makeText(getBaseContext(), jsonResult.getString("message"), Toast.LENGTH_SHORT).show();
                                onLoginFailed();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "errorResponse "+error, Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                        onLoginFailed();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD, password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }
    public void dontLogin(View view){
        Intent intent = new Intent();
        setResult(getResources().getInteger(R.integer.loginResultSkipCode), intent);
        finish();
    }

    public void gotoRegister(View view){
        Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void onLoginSuccess(int userId){
//        Toast.makeText(this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userId",userId);
        editor.apply();

        Intent intent = new Intent();
        setResult(getResources().getInteger(R.integer.loginResultSuccessCode), intent);
        finish();
    }

    private void onLoginFailed(){
//        Toast.makeText(this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        loginMessage.setTextColor(ContextCompat.getColor(this, R.color.colorError));
        loginMessage.setText(getResources().getString(R.string.login_message_wrong_info));
    }
}
