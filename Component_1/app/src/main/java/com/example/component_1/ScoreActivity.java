package com.example.component_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    TextView tvName, tvScore;
    Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        String pName = getIntent().getStringExtra("pName");
        int score = getIntent().getIntExtra("score",0);

        setTitle(getResources().getText(R.string.app_name) + ": " + pName);

        tvName = (TextView) findViewById(R.id.tv_sa_name);
        tvScore = (TextView) findViewById(R.id.tv_sa_score);

        btnFinish = (Button)findViewById(R.id.btn_sa_finish);

        tvName.setText(pName);
        tvScore.setText(Integer.toString(score));

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}