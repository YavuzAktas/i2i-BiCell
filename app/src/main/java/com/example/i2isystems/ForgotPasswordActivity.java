package com.example.i2isystems;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText editTextEmail;
    Button btnSave;
    TextView txtWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.editTextEmail);
        btnSave = findViewById(R.id.btnSave);
        txtWarn = findViewById(R.id.txtWarn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextEmail.getText()))
                {
                    txtWarn.setText("Lütfen boş değer bırakmayınız.");
                }
                else if(!isValidEmailId(editTextEmail.getText().toString().trim()))
                {
                    txtWarn.setText("Lütfen geçerli bir değer giriniz.");
                }
                else
                {
                    //API REQUEST EKLENECEK

                    startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                }

            }
        });
    }
    public boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}