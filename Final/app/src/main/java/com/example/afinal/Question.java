package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question extends AppCompatActivity {

    ArrayList<QuestionData> quesList;
    TextView question ;
    Button cfBtn ;
    RadioGroup radioGroup ;
    RadioButton answearA ;
    RadioButton answearB ;
    RadioButton answearC ;
    RadioButton answearD ;

    private int currentQues = 0;
    private String selectOpt =""; //user answear ( between A-D)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        cfBtn = findViewById(R.id.cfBtn);
        radioGroup = findViewById(R.id.radio_group);
        answearA = findViewById(R.id.radio_ansA);
        answearB = findViewById(R.id.radio_ansB);
        answearC = findViewById(R.id.radio_ansC);
        answearD = findViewById(R.id.radio_ansD);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            quesList = (ArrayList<QuestionData>) bundle.getSerializable("questionList");
            GlobalData.resetScore();
            selectQuestion(0);
            cfBtn.setOnClickListener(view ->{
                if (!selectOpt.equalsIgnoreCase("")) {
                    if (selectOpt.equalsIgnoreCase(quesList.get(currentQues).result)) {
                        selectOpt = "";

                        if(quesList.get(currentQues).level.equalsIgnoreCase("Dễ"))  GlobalData.increaseEasy();
                        else GlobalData.increaseHard();

                        currentQues++;

                        if(currentQues <= quesList.size()-1){
                            selectQuestion(currentQues); //next question
                        }
                        else {
                            finishQuiz();
                        }
                    } else {
                        selectOpt = "";
                        finishQuiz();
                    }
                } else {
                    Toast.makeText(Question.this, "Please choose your answear", Toast.LENGTH_LONG).show();
                }

            });

            radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
                if (i == R.id.radio_ansA) {
                    selectOpt = "A";
                } else if (i == R.id.radio_ansB) {
                    selectOpt = "B";
                } else if (i == R.id.radio_ansC) {
                    selectOpt = "C";
                }
                else {
                    selectOpt = "D";
                }
            });
        }



    }

    private void finishQuiz(){
        Intent action = new Intent(this, Result.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("questionList", quesList);
        action.putExtras(bundle);
        startActivity(action);
        finish();
    }

    private void selectQuestion(int questionListPos){

        question.setText(quesList.get(questionListPos).question);
        answearA.setText(quesList.get(questionListPos).answearA);
        answearB.setText(quesList.get(questionListPos).answearB);
        answearC.setText(quesList.get(questionListPos).answearC);
        answearD.setText(quesList.get(questionListPos).answearD);


    }
}