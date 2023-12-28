package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    ArrayList<QuestionData> quesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView correct_ans = findViewById(R.id.correct_ans);
        Button doneBtn = findViewById(R.id.doneBtn);
        Button playagainBtn = findViewById(R.id.playagainBtn);
        Button shareBtn = findViewById(R.id.shareBtn);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            quesList = (ArrayList<QuestionData>) bundle.getSerializable("questionList");

            correct_ans.setText(String.valueOf(GlobalData.score));

            doneBtn.setOnClickListener(view -> {
                Intent action = new Intent(this, MainActivity.class);
                startActivity(action);
            });

            playagainBtn.setOnClickListener(view -> {
                Intent action = new Intent(this, Question.class);

                Bundle newBundle = new Bundle();
                newBundle.putSerializable("questionList", quesList);
                action.putExtras(newBundle);
                startActivity(action);
                finish();
            });

            shareBtn.setOnClickListener(view -> {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = String.valueOf(GlobalData.score);
                String shareSub = "Subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "share choosing"));
            });
        }
    }
}