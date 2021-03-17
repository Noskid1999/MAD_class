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
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity {

    Button btnNext, btnPrev;
    RadioGroup rgQuiz;

    ArrayList<Question> questionList;

    RadioButton[] rbList = new RadioButton[4];
    TextView tvQuestion, tvQuestionCount;

    ScrollView quizScroll;

    int currentQuestionIndex = 0;
    ArrayList<String> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("APP", "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
//      Get the name and the email from the name activity intent
        String pName = getIntent().getStringExtra("pName");

        setTitle(getResources().getText(R.string.app_name) + ": " + pName);
//      Default the question index to 0 and change as required
        currentQuestionIndex = 0;
//      Get the reference to all the required fields in the quiz
        tvQuestion = (TextView) findViewById(R.id.tv_qa_txt);
        tvQuestionCount = (TextView) findViewById(R.id.tv_qa_questionCount);
        quizScroll = (ScrollView) findViewById(R.id.sv_qa_quizScroll);

        rgQuiz = (RadioGroup) findViewById(R.id.rg_qa);
        rbList[0] = (RadioButton) findViewById(R.id.rb_qa_opt1);
        rbList[1] = (RadioButton) findViewById(R.id.rb_qa_opt2);
        rbList[2] = (RadioButton) findViewById(R.id.rb_qa_opt3);
        rbList[3] = (RadioButton) findViewById(R.id.rb_qa_opt4);

        btnNext = (Button) findViewById(R.id.btn_qa_next);
        btnPrev = (Button) findViewById(R.id.btn_qa_previous);
//      Get the list of question
        questionList = this.getQuestions();
//      Check if there is a saved instance state. If present, recover the state of the app
        if (savedInstanceState != null) {
            this.selectedAnswers = (ArrayList<String>) Arrays.asList(savedInstanceState.getStringArray("selectedAnswers"));
            this.currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex", 0);
        } else {
            for (int i = 0; i < questionList.size(); i++) selectedAnswers.add(null);
        }


//      Set the starting question
        this.displayQuestion(currentQuestionIndex);
//      Set the onclick lister for the next and the previous buttons
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Get the ID of the radio button from the radio group of the quiz
                int answerId = rgQuiz.getCheckedRadioButtonId();
//              Allow to proceed forward only if the user has selected an option.
                if (answerId == -1) {
                    tvQuestion.setError("Please select an option");
                    return;
                }
//              Get the selected radio button
                RadioButton answer = (RadioButton) findViewById(answerId);
//              Set the answer in the selected answer arr.
                selectedAnswers.set(currentQuestionIndex, answer.getText().toString());
//              End of the quiz
                if (currentQuestionIndex == (questionList.size() - 1)) {
//                  Create an intent to pass to the score activity
                    Intent scoreIntent = new Intent(QuizActivity.this, ScoreActivity.class);
//                  Put the name and score to the intent and start the intent
                    scoreIntent.putExtra("pName", pName);
                    scoreIntent.putExtra("score", getScore());
                    scoreIntent.putExtra("totalCount", questionList.size());

                    startActivity(scoreIntent);
                    finish();
                }
//              If not end of the quiz, continue to next question
                else {
                    currentQuestionIndex++;
                    displayQuestion(currentQuestionIndex);
                }
            }

        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Go to previous only if not the first question
                if (currentQuestionIndex != 0) {
                    currentQuestionIndex--;
                    displayQuestion(currentQuestionIndex);
                }
            }
        });
    }

    /**
     * Parses the questions from the string.xml and creates a question array list
     *
     * @return Arraylist of questions
     */
    protected ArrayList<Question> getQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();
//      Get the questions array from the string.xml
        String[] questions = getResources().getStringArray(R.array.questions);

        for (int i = 0; i < questions.length; i++) {
//            Split the questions using required delimiter, here ","
            String[] split = questions[i].split(",");

            questionList.add(new Question(split.clone()));
        }
        return questionList;
    }

    /**
     * Displays the required question along with options
     *
     * @param reqQuestionIndex Current question index among the questions
     */
    void displayQuestion(int reqQuestionIndex) {
//        Get the required question
        Question question = questionList.get(reqQuestionIndex);
//        Set the question count
        tvQuestionCount.setText("Question " + (reqQuestionIndex + 1) + "/ " + questionList.size());
//        Set the question and answers text in the radio group and btn
        tvQuestion.setText(question.getQuestion());
//      Set options in the RadioButtons
        for (int i = 0; i < 4; i++) {
            rbList[i].setText(question.getOptionList().get(i));
        }
//      Refocus to the top
        quizScroll.fullScroll(ScrollView.FOCUS_UP);
//        Clear the previous selected option and reset the error if set
        rgQuiz.clearCheck();
        tvQuestion.setError(null);

//        Set preselected answer if present
        if (selectedAnswers.size() > 0 && selectedAnswers.get(reqQuestionIndex) != null) {
            for (int i = 0; i < 4; i++) {
//                if ans matches, set check/mark the current answer
                if (question.getOptionList().get(i).equals(selectedAnswers.get(reqQuestionIndex))) {
                    ((RadioButton) rgQuiz.getChildAt(i)).setChecked(true);
                    break;
                }
            }
        }

    }

    /**
     * Get the score from the selected answers
     *
     * @return Score of the user
     */
    protected int getScore() {
        int score = 0;
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getAnswer().equals(selectedAnswers.get(i))) {
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
        outState.putStringArray("selectedAnswers", selectedAnswers.toArray(new String[selectedAnswers.size()]));
    }
}