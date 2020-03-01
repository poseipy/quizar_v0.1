package com.example.quizar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestLevelActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference("test");

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
                //
            }
        });
    }
}
