package com.example.component_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView tvScore, tvTotal;
    Button btnRestart, btnFinish;

    ImageView outputImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        String pName = getIntent().getStringExtra("pName");
        int score = getIntent().getIntExtra("score", 0);
        int totalCount = getIntent().getIntExtra("totalCount", 5);

        setTitle(getResources().getText(R.string.app_name) + ": " + pName);

        tvScore = (TextView) findViewById(R.id.tv_sa_score);
        tvTotal = (TextView) findViewById(R.id.tv_sa_total);

        outputImg = (ImageView) findViewById(R.id.iv_sa_outputImg);

        btnRestart = (Button) findViewById(R.id.btn_sa_restart);
        btnFinish = (Button) findViewById(R.id.btn_sa_finish);

//      Check the result and set the image and score color as required
        if (((float) score / (float) totalCount) > 0.4) {
            outputImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.score_pass));
            tvScore.setTextColor(Color.parseColor("#28A745"));
        } else {
            outputImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.score_fail));
            tvScore.setTextColor(Color.parseColor("#DC3545"));
        }

//      Set the score and the total score
        tvScore.setText(Integer.toString(score));
        tvTotal.setText(Integer.toString(totalCount));
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}