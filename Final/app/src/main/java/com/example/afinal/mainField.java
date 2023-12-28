package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class mainField extends AppCompatActivity {
    ArrayList<QuestionData> arr; //creating questionList
    ArrayList<FieldData> listQuestion;
    QuestionListViewAdapter questionListViewAdapter;
    ListView listViewProduct;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String quesLevel ="Dễ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_field);

        TextView score = findViewById(R.id.score);
        SwitchMaterial switch1 = findViewById(R.id.switch_level);

        switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (switch1.isChecked())
            {
                quesLevel = "Khó";
            }
            else
            {
                quesLevel = "Dễ";
            }
        });

        score.setText(String.valueOf(GlobalData.score));

        arr = new ArrayList<>();

        db.collection("questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "Success to get data", Toast.LENGTH_SHORT).show();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                QuestionData quesdata = new QuestionData(
                                        String.valueOf(document.getData().get("field")),
                                        document.getId(),
                                        String.valueOf(document.getData().get("question")),
                                        String.valueOf(document.getData().get("answearA")),
                                        String.valueOf(document.getData().get("answearB")),
                                        String.valueOf(document.getData().get("answearC")),
                                        String.valueOf(document.getData().get("answearD")),
                                        String.valueOf(document.getData().get("result")),
                                        String.valueOf(document.getData().get("level"))
                                );
                                arr.add(quesdata);

                            }
//                            Log.d("Firestore", String.valueOf(arr.size()));
                        } else {
                            Log.d("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });


        listQuestion = new ArrayList<>();

        listQuestion.add(new FieldData(
                1,
                "Địa Lý",
                "Các câu hỏi về các vùng đất, địa hình, dân cư và các hiện tượng trên trái đất",
                R.drawable.dia_ly
        ));
        listQuestion.add(new FieldData(
                2,
                "Lịch Sử",
                "Các câu hỏi về sự kiện liên quan đến con người",
                R.drawable.lich_su
        ));
        listQuestion.add(new FieldData(
                3,
                "Khoa Học",
                "Các câu hỏi về những định luật, cấu trúc và cách vận hành của thế giới tự nhiên",
                R.drawable.khoa_hoc
        ));
        listQuestion.add(new FieldData(
                4,
                "Nghệ Thuật",
                "Các câu hỏi về nghệ thuật",
                R.drawable.nghe_thuat
        ));

        if(listQuestion.size() > 0){
            questionListViewAdapter = new QuestionListViewAdapter(listQuestion);

            listViewProduct = findViewById(R.id.listField);
            listViewProduct.setAdapter(questionListViewAdapter);
        }

        Button quesListBtn = findViewById(R.id.quesListBtn);

        quesListBtn.setOnClickListener(view ->{
            Intent action = new Intent(this, QuestionList.class);
            startActivity(action);
        });

        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FieldData field = (FieldData) questionListViewAdapter.getItem(position);

                ArrayList<QuestionData> quesList= new ArrayList<>();

                for(int i=0;i<arr.size();i++){
                    if(arr.get(i).field.equalsIgnoreCase(field.name.toString()) && arr.get(i).level.equalsIgnoreCase(quesLevel)){
                        quesList.add(arr.get(i));
                    }
                }

                Intent action = new Intent(mainField.this, Question.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("questionList", quesList);
                action.putExtras(bundle);
                startActivity(action);
                finish();
            }
        });
    }


}