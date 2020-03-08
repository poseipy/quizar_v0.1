package com.example.quizar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LevelCreation extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();
    DatabaseReference mLevelsRef = mRootRef.child("levels_data").push();
    DatabaseReference mCategoryRef = mRootRef.child("level_category");
    DatabaseReference mTittleRef = mRootRef.child("levels_tittle");

    EditText levels_Tittle;
    EditText question_image;
    EditText right_answer;
    EditText answer_1;
    EditText answer_2;
    EditText answer_3;
    EditText question;
    Button create_level;
    Spinner spinner_category;

    String levels_id = mLevelsRef.getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_creation);
        question_image = findViewById(R.id.question_image);
        right_answer = findViewById(R.id.right_answer);
        answer_1 = findViewById(R.id.answer_1);
        answer_2 = findViewById(R.id.answer_2);
        answer_3 = findViewById(R.id.answer_3);
        question = findViewById(R.id.question);
        create_level = findViewById(R.id.create_level);
        spinner_category = findViewById(R.id.spinnerCateogry);
        levels_Tittle = findViewById(R.id.question_tittle);

        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this, R.array.Category, android.R.layout.simple_spinner_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(category);


        insertData();
    }

    private void insertData(){
        create_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_question_image = question_image.getText().toString();
                String s_right_answer = right_answer.getText().toString();
                String s_answer_1 = answer_1.getText().toString();
                String s_answer_2 = answer_2.getText().toString();
                String s_answer_3 = answer_3.getText().toString();
                String s_question = question.getText().toString();
                String s_category = spinner_category.getSelectedItem().toString();
                LevelData levelData = new LevelData(s_question_image, s_right_answer, s_answer_1, s_answer_2, s_answer_3, s_question,s_category);

                mCategoryRef.child(levels_id).setValue(s_category);
                mLevelsRef.setValue(levelData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Succes",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
