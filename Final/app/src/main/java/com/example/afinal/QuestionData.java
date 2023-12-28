package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class QuestionData implements Serializable {
    public String questionID;
     public String field;
     public String question;
     public String answearA;
    public String answearB;
    public String answearC;
    public String answearD;
     public String result;
     public String level;
     public String userAnswear;


    public QuestionData(String field,String questionID, String question, String answearA,String answearB,String answearC,String answearD, String result, String level) {
        this.field=field;
        this.questionID = questionID;
        this.question = question;
        this.answearA = answearA;
        this.answearB = answearB;
        this.answearC = answearC;
        this.answearD = answearD;
        this.result = result;
        this.level= level;
    }


}