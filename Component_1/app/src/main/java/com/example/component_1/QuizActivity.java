package com.example.component_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    Button btnNext, btnPrev;
    RadioGroup rgQuiz;

    String[][] questionList;

    RadioButton rbOpt1, rbOpt2, rbOpt3, rbOpt4;
    TextView tvQuestion;

    int currentQuestionIndex;
    String[] selectedAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String pName = getIntent().getStringExtra("pName");
        String pEmail = getIntent().getStringExtra("pEmail");

        setTitle(getResources().getText(R.string.app_name) + ": " + pName);

        currentQuestionIndex = 0;
        selectedAnswers = new String[5];

        tvQuestion = (TextView) findViewById(R.id.tv_qa_txt);

        rgQuiz = (RadioGroup) findViewById(R.id.rg_qa);

        rbOpt1 = (RadioButton) findViewById(R.id.rb_qa_opt1);
        rbOpt2 = (RadioButton) findViewById(R.id.rb_qa_opt2);
        rbOpt3 = (RadioButton) findViewById(R.id.rb_qa_opt3);
        rbOpt4 = (RadioButton) findViewById(R.id.rb_qa_opt4);


        btnNext = (Button) findViewById(R.id.btn_qa_next);
        btnPrev = (Button) findViewById(R.id.btn_qa_previous);

        questionList = this.getQuestions();

//      Set the starting question
        this.displayQuestion(questionList[currentQuestionIndex]);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int answerId = rgQuiz.getCheckedRadioButtonId();
                RadioButton answer = (RadioButton) findViewById(answerId);

                selectedAnswers[currentQuestionIndex] = answer.getText().toString();

                if (currentQuestionIndex == 4) {
                    Intent scoreIntent = new Intent(QuizActivity.this, ScoreActivity.class);

                    scoreIntent.putExtra("pName", pName);
                    scoreIntent.putExtra("pEmail", pEmail);
                    scoreIntent.putExtra("score", getScore());

                    startActivity(scoreIntent);
                    finish();
                } else {
                    currentQuestionIndex++;
                    displayQuestion(questionList[currentQuestionIndex]);
                }
            }

        });
    }

    protected String[][] getQuestions() {
        String[][] questionList = new String[5][6];

        String[] questions = getResources().getStringArray(R.array.questions);

        for (int i = 0; i < 5; i++) {
            String[] split = questions[i].split(",");

            questionList[i] = split.clone();
        }
        return questionList;
    }

    void displayQuestion(String[] question) {
        tvQuestion.setText(question[0]);

        rbOpt1.setText(question[1]);
        rbOpt2.setText(question[2]);
        rbOpt3.setText(question[3]);
        rbOpt4.setText(question[4]);

        rgQuiz.clearCheck();
    }

    protected int getScore() {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            if (questionList[i][5].equals(selectedAnswers[i])) {
                score++;
            }
        }
        return score;
    }
}