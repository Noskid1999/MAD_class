package com.example.component_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class QuizActivity1 extends AppCompatActivity {

    Button btnNext, btnPrev;
    RadioGroup rgQuiz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        btnNext = (Button)findViewById(R.id.btn_qa_next);
        btnPrev = (Button)findViewById(R.id.btn_qa_previous);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity1.this, ScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}