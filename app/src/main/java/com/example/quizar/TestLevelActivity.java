package com.example.quizar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestLevelActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();

    EditText question_image;
    EditText right_answer;
    EditText answer_1;
    EditText answer_2;
    EditText answer_3;
    EditText question;
    Button create_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_level);
        question_image = findViewById(R.id.question_image);
        right_answer = findViewById(R.id.right_answer);
        answer_1 = findViewById(R.id.answer_1);
        answer_2 = findViewById(R.id.answer_2);
        answer_3 = findViewById(R.id.answer_3);
        question = findViewById(R.id.question);
        create_level = findViewById(R.id.create_level);

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
                LevelData levelData = new LevelData(s_question_image, s_right_answer, s_answer_1, s_answer_2,  s_answer_3,  s_question);

                long mDateTime = 999999999999L - System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

                mRootRef.child("levels_data").child(mOrderTime).setValue(levelData).addOnSuccessListener(new OnSuccessListener<Void>() {
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
