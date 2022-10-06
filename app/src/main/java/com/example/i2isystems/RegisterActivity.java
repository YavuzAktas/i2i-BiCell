package com.example.i2isystems;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ForgotPasswordActivity forgotPasswordActivity;
    private String msisdn;
    private String email;
    private String name;
    private Integer packageId;
    private String password;
    private String password2;
    private String surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextPassword2 = findViewById(R.id.editTextPassword2);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        Button btnSave = findViewById(R.id.btnSave);
        TextView txtWarn = findViewById(R.id.txtWarn);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSurname = findViewById(R.id.editTextSurname);
        Random rand = new Random();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                msisdn = editTextPhone.getText().toString();
                email = editTextEmail.getText().toString();
                name = editTextName.getText().toString();
                password = editTextPassword.getText().toString();
                password2 = editTextPassword2.getText().toString();
                surname = editTextSurname.getText().toString();
                packageId =rand.nextInt(5)+21;

                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(msisdn) || TextUtils.isEmpty(email) || TextUtils.isEmpty(editTextPassword2.getText())) {
                    txtWarn.setText("Lütfen boş değer bırakmayınız.");
                } else if (!password.equals(password2)) {
                    txtWarn.setText("Şifreler eşit değil.");
                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(email);
                    registerRequest.setMsisdn(msisdn);
                    registerRequest.setPassword(password);
                    registerRequest.setName(name);
                    registerRequest.setSurname(surname);
                    registerRequest.setSubscId(0);
                    registerRequest.setPackadgeId(packageId);
                    Call<RegisterRequest> registerRequestCall = RetrofitClientInstance.getPackageId()
                            .register(registerRequest);
                    registerRequestCall.enqueue(new Callback<RegisterRequest>() {
                        @Override
                        public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {

                            if (response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Kayıt Başarılı!", Toast.LENGTH_LONG).show();
                                RegisterRequest registerReq = response.body();


                            } else {
                                Toast.makeText(RegisterActivity.this, "Kayıt başarısız", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterRequest> call, Throwable t) {
                            //denemek için ekledim
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    });

                }

            }
        });
    }
}