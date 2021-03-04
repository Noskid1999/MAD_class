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
                Intent quizIntent = new Intent(NameActivity.this, QuizActivity.class);

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();

                quizIntent.putExtra("pName",name);
                quizIntent.putExtra("pEmail",email);

                startActivity(quizIntent);
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