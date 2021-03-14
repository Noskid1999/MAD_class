package com.example.component_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    Button btnNext, btnPrev;
    RadioGroup rgQuiz;

    ArrayList<Question> questionList;

    RadioButton[] rbList = new RadioButton[4];
    //    RadioButton rbOpt1, rbOpt2, rbOpt3, rbOpt4;
    TextView tvQuestion;

    int currentQuestionIndex = 0;
    String[] selectedAnswers = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("APP", "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String pName = getIntent().getStringExtra("pName");
        String pEmail = getIntent().getStringExtra("pEmail");

        setTitle(getResources().getText(R.string.app_name) + ": " + pName);

        currentQuestionIndex = 0;

        tvQuestion = (TextView) findViewById(R.id.tv_qa_txt);

        rgQuiz = (RadioGroup) findViewById(R.id.rg_qa);
        rbList[0] = (RadioButton) findViewById(R.id.rb_qa_opt1);
        rbList[1] = (RadioButton) findViewById(R.id.rb_qa_opt2);
        rbList[2] = (RadioButton) findViewById(R.id.rb_qa_opt3);
        rbList[3] = (RadioButton) findViewById(R.id.rb_qa_opt4);

        btnNext = (Button) findViewById(R.id.btn_qa_next);
        btnPrev = (Button) findViewById(R.id.btn_qa_previous);

        questionList = this.getQuestions();

        if (savedInstanceState != null) {
            this.selectedAnswers = savedInstanceState.getStringArray("selectedAnswers");
            this.currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex", 0);
        }


//      Set the starting question
        this.displayQuestion(currentQuestionIndex);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int answerId = rgQuiz.getCheckedRadioButtonId();

                if (answerId == -1) {
                    tvQuestion.setError("Please select an option");
                    return;
                }

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
                    displayQuestion(currentQuestionIndex);
                }
            }

        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex != 0) {
                    currentQuestionIndex--;
                    displayQuestion(currentQuestionIndex);
                }
            }
        });
    }

    protected ArrayList<Question> getQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();

        String[] questions = getResources().getStringArray(R.array.questions);

        for (int i = 0; i < 5; i++) {
            String[] split = questions[i].split(",");

            questionList.add(new Question(split.clone()));
        }
        return questionList;
    }

    void displayQuestion(int reqQuestionIndex) {
//        Get the required question
        Question question = questionList.get(reqQuestionIndex);
//        Set the question and answers text in the radio group and btn
        tvQuestion.setText(question.getQuestion());
//      Set options
        for (int i = 0; i < 4; i++) {
            rbList[i].setText(question.getOptionList().get(i));
        }

//        Clear the previous selected option and reset the error if set
        rgQuiz.clearCheck();
        tvQuestion.setError(null);

//        Set preselected answer
        if (selectedAnswers[reqQuestionIndex] != null) {
            for (int i = 0; i < 4; i++) {
//                if ans matches, set check/mark the current answer
                if (question.getOptionList().get(0).equals(selectedAnswers[reqQuestionIndex])) {
                    ((RadioButton) rgQuiz.getChildAt(i)).setChecked(true);
                    break;
                }
            }
        }

    }

    protected int getScore() {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            if (questionList.get(i).getAnswer().equals(selectedAnswers[i])) {
                score++;
            }
        }
        return score;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("APP", "OnState() called");
//        Save the state of the question i.e. the current index of the question
        outState.putInt("currentQuestionIndex", currentQuestionIndex);
        outState.putStringArray("selectedAnswers", selectedAnswers);
    }
}