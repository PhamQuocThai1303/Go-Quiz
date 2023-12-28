package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

import java.util.ArrayList;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class QuestionList extends AppCompatActivity {
    ArrayList<QuestionData> baseArr= new ArrayList<>();
    ArrayList<QuestionData> arr= new ArrayList<>();
    QuestionAdapter questionAdapter;
    ListView listViewQuestion;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String chooseField ="";

    private int quesId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        loadDataFromFirestore();

    }

    private void loadDataFromFirestore() {
        db.collection("questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String getField = String.valueOf(document.getData().get("field"));
                                String getQuestion = String.valueOf(document.getData().get("question"));
                                String getAnswearA =String.valueOf(document.getData().get("answearA"));
                                String getAnswearB =String.valueOf(document.getData().get("answearB"));
                                String getAnswearC =String.valueOf(document.getData().get("answearC"));
                                String getAnswearD =String.valueOf(document.getData().get("answearD"));
                                String getResult = String.valueOf(document.getData().get("result"));
                                String getLevel = String.valueOf(document.getData().get("level"));
                                String getId = String.valueOf(quesId);
                                quesId++;

                                arr.add(new QuestionData(
                                        getField,
                                        getId,
                                        getQuestion,
                                        getAnswearA,
                                        getAnswearB,
                                        getAnswearC,
                                        getAnswearD,
                                        getResult,
                                        getLevel
                                ));
                                baseArr.add(new QuestionData(
                                        getField,
                                        getId,
                                        getQuestion,
                                        getAnswearA,
                                        getAnswearB,
                                        getAnswearC,
                                        getAnswearD,
                                        getResult,
                                        getLevel
                                ));

                            }
                            processLoadedData(arr);
                        } else {
                            Log.d("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void processLoadedData( ArrayList<QuestionData> data) {
        // Thực hiện các hành động tiếp theo với dữ liệu đã được tải xuống thành công
        if(arr.size()>0) {
            questionAdapter = new QuestionAdapter(arr);
            listViewQuestion = findViewById(R.id.questionList);
            listViewQuestion.setAdapter(questionAdapter);
        }

        TextInputLayout textField = findViewById(R.id.dropdown);

        String[] list = {"Địa Lý", "Lịch Sử", "Khoa Học", "Nghệ Thuật"};
        ((MaterialAutoCompleteTextView) textField.getEditText()).setSimpleItems(list);

        ((MaterialAutoCompleteTextView) textField.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                chooseField = textField.getEditText().getText().toString();
                if(chooseField.equalsIgnoreCase("")){
                    arr.clear();
                    for(int i=0;i<baseArr.size();i++){
                            arr.add(baseArr.get(i));
                    }

                    questionAdapter = new QuestionAdapter(arr);
                    listViewQuestion = findViewById(R.id.questionList);
                    listViewQuestion.setAdapter(questionAdapter);
                }
                else {
                    arr.clear();
                    for(int i=0;i<baseArr.size();i++){
                        if(baseArr.get(i).field.equalsIgnoreCase(chooseField)){

                            arr.add(baseArr.get(i));
                        }
                    }

                    questionAdapter = new QuestionAdapter(arr);
                    listViewQuestion = findViewById(R.id.questionList);
                    listViewQuestion.setAdapter(questionAdapter);
                }

            }

        });


        listViewQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent action = new Intent(QuestionList.this, DetailQuestion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("questionData", arr.get(position));
                action.putExtras(bundle);
                startActivity(action);
//                Toast.makeText(MainActivity.this, product.name, Toast.LENGTH_LONG).show();
            }
        });
    }

}