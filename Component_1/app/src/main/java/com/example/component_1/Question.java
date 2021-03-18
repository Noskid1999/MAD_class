package com.example.component_1;

import android.util.Log;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> optionList;
    private String answer;

    public Question(String[] question){
        this.question = question[0];
        this.optionList = new ArrayList<String>();
        this.optionList.add(question[1]);
        this.optionList.add(question[2]);
        this.optionList.add(question[3]);
        this.optionList.add(question[4]);

        this.answer = question[5];
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
