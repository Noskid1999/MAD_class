package com.example.component_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {
    Button startBtn, cancelBtn;
    EditText etName, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        startBtn = (Button) findViewById(R.id.btn_na_submit_btn);
        cancelBtn = (Button) findViewById(R.id.btn_na_cancel_btn);

        etName = (EditText) findViewById(R.id.et_na_name);
        etEmail = (EditText) findViewById(R.id.tv_na_email);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz1 = new Intent(NameActivity.this, QuizActivity1.class);

                String name = etName.toString();
                String email = etEmail.toString();

                quiz1.putExtra("name",name);
                quiz1.putExtra("email",email);

                startActivity(quiz1);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


    }
}