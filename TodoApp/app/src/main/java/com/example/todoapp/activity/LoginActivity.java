package com.example.todoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapp.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;

    EditText etEmail, etPassword;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_al_login);

        etEmail = (EditText) findViewById(R.id.et_al_email);
        etPassword = (EditText) findViewById(R.id.et_al_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError(getResources().getString(R.string.empty_email));
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError(getResources().getString(R.string.empty_password));
                    return;
                }
                if (!email.equals("test@gmail.com")) {
                    etEmail.setError(getResources().getString(R.string.invalid_email));
                    return;
                } else if (!password.equals("12345")) {
                    etPassword.setError(getResources().getString(R.string.invalid_password));
                    return;
                }
//                Set the preferences to authenticated true for future app opening
                SharedPreferences preference = getApplicationContext().getSharedPreferences("todo_pref", 0);
                SharedPreferences.Editor editor = preference.edit();
                editor.putBoolean("isAuthenticated", true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}