package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetailQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);
        TextView question = findViewById(R.id.question);
        Button level = findViewById(R.id.level);
        TextView quesA = findViewById(R.id.quesA);
        TextView quesB = findViewById(R.id.quesB);
        TextView quesC = findViewById(R.id.quesC);
        TextView quesD = findViewById(R.id.quesD);
        TextView result = findViewById(R.id.result);

        Bundle bundle = getIntent().getExtras();
    if(bundle != null){
        QuestionData ques = (QuestionData) bundle.getSerializable("questionData");
        question.setText(ques.question.toString());
        level.setText(ques.level.toString());
        quesA.setText(ques.answearA.toString());
        quesB.setText(ques.answearB.toString());
        quesC.setText(ques.answearC.toString());
        quesD.setText(ques.answearD.toString());
        switch (ques.result.toString()) {
            case "A":
                result.setText(ques.answearA.toString());
                break;
            case "B":
                result.setText(ques.answearB.toString());
                break;
            case "C":
                result.setText(ques.answearC.toString());
                break;
            case "D":
                result.setText(ques.answearD.toString());
                break;
            default:
                result.setText(ques.result.toString());
        }
    }

    }
}