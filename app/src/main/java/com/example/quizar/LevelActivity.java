package com.example.quizar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LevelActivity extends AppCompatActivity{

    private TextView countLabel;
    private TextView questionText;
    private ImageView questionImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mLevelsRef = mRootRef.child("levels");
    private DatabaseReference mOneRef = mLevelsRef.child("1");

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    DatabaseReference[][] quizData = new DatabaseReference[][]{
            {mOneRef},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        questionText = findViewById(R.id.questionText);
        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        for (DatabaseReference[] quizDatum : quizData) {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(String.valueOf(quizDatum[0])); // Image Name
            tmpArray.add(String.valueOf(quizDatum[1])); // Right Answer
            tmpArray.add(String.valueOf(quizDatum[2])); // Choice1
            tmpArray.add(String.valueOf(quizDatum[3])); // Choice2
            tmpArray.add(String.valueOf(quizDatum[4])); // Choice3
            tmpArray.add(String.valueOf(quizDatum[5])); //question
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    @SuppressLint("SetTextI18n")
    public void showNextQuiz() {

        countLabel.setText("Q" + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAnswer = quiz.get(1);
        questionText.setText(quiz.get(5));

        //help i'm stuck

        quiz.remove(0);
        quiz.remove(4);
        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct!!
            alertTitle = "Correct!";
            rightAnswerCount++;

        } else {
            // Wrong
            alertTitle = "Wrong...";
        }

        // Create Dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizArray.size() < 1) {
                    showResult();

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(rightAnswerCount+ "/" + quizData.length);
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }


}

