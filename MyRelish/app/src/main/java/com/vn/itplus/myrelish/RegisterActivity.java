package com.vn.itplus.myrelish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = RegisterActivity.class.getSimpleName();
    EditText editName,editEmail,editPass,editRepass;
    Button btnCreate,btnClose;
    private ProgressDialog pDialog;

    public static final String KEY_USERNAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPass.getText().length()<8){
                    Toast.makeText(RegisterActivity.this, "Password phải lớn hơn 8 ký tự", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "editPass"+editPass.getText()+"\n"+"editRpass"+editRepass.getText(), Toast.LENGTH_SHORT).show();
                    if((editPass.getText().toString().equals(editRepass.getText().toString()))){
                        String name = editName.getText().toString().trim();
                        String password = editPass.getText().toString().trim();
                        String email = editEmail.getText().toString().trim();
                        registerUser(name,password,email);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            editEmail.setError("Email sai định dạng!");
        }
        return false;
    }
    private void registerUser(final String name, final String password, final String email) {
        final String url = getResources().getString(R.string.server_url)+"/user/api/register";
        if (checkEditText(editName) && checkEditText(editPass) && checkEditText(editEmail) && isValidEmail(email)) {
            pDialog.show();
            StringRequest registerRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pDialog.dismiss();
                            try{
                                JSONObject jsonResult = new JSONObject(response);
                                if(jsonResult.getBoolean("result")){
                                    Toast.makeText(getBaseContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(getBaseContext(), jsonResult.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "errorResponse"+error, Toast.LENGTH_SHORT).show();
                            VolleyLog.d(TAG, "error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, name);
                    params.put(KEY_PASSWORD, password);
                    params.put(KEY_EMAIL, email);
                    long millis=System.currentTimeMillis();
                    Date date = new Date(millis);
                    params.put("birthday", date.toString());
                    params.put("gender", "0");
                    params.put("bio", "");
                    params.put("image", "");
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }
    private void mapping() {
        editName = findViewById(R.id.txt_name_r);
        editEmail = findViewById(R.id.txt_email_r);
        editPass = findViewById(R.id.txt_pass_r);
        editRepass = findViewById(R.id.txt_Rpass_r);
        btnCreate = findViewById(R.id.btn_create_account);
        btnClose = findViewById(R.id.btn_skip);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }
}
