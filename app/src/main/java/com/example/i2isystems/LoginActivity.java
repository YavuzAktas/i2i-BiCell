package com.example.i2isystems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextPhone,editTextPassword;
    private Button btnSave;
    private TextView txtWarn, txtForgotPassword,txtCreate;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    String editPhone,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnSave = findViewById(R.id.btnSave);
        txtWarn = findViewById(R.id.txtWarn);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        txtCreate = findViewById(R.id.create);


        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            editTextPhone.setText(loginPreferences.getString("phone", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextPassword.getText()) || TextUtils.isEmpty(editTextPhone.getText()))
                {
                    txtWarn.setText("Lütfen boş değer bırakmayınız.");
                }
                else {
                    editPhone = editTextPhone.getText().toString();
                    editPassword = editTextPassword.getText().toString();
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setMsisdn(editPhone);
                    loginRequest.setPassword(editPassword);
                    Call<LoginRequest> loginRequestCall = RetrofitClientInstance.getUserService().login(loginRequest);
                    loginRequestCall.enqueue(new Callback<LoginRequest>() {
                        @Override
                        public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                            if (response.isSuccessful()) {
                                LoginRequest loginRequest = response.body();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            } else {
                                txtWarn.setText("Kullanıcı adı veya şifre hatalı");
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginRequest> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Hata!  " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            System.out.println(t.getLocalizedMessage());
                        }
                    });
                }

                    // REMEMBER ME
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextPhone.getWindowToken(), 0);

                    if (saveLoginCheckBox.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("phone", editTextPhone.getText().toString());
                        loginPrefsEditor.putString("password", editTextPassword.getText().toString());
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }

        });

        txtCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }

        });

    }

}
