package com.example.quizar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class
LevelCreation extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();
    DatabaseReference mLevelsRef = mRootRef.child("levels_data");
    DatabaseReference mLevelsInfoRef = mRootRef.child("levels_info");
//    DatabaseReference mTittleRef = mRootRef.child("levels_tittle");

    EditText levels_Tittle;
    EditText question_image;
    EditText right_answer;
    EditText answer_1;
    EditText answer_2;
    EditText answer_3;
    EditText question;
    Button create_level;
    Spinner spinner_category;

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

    public static class LevelFragment extends Fragment {

        private FragmentListener listener;

        public interface FragmentListener {
            void onInputSent(CharSequence input);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_level_creation, container, false);

        }
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
                String level_name = levels_Tittle.getText().toString();

                final LevelTitle levelTitle = new LevelTitle(level_name);
                final LevelData levelData = new LevelData(s_question_image, s_right_answer, s_answer_1, s_answer_2, s_answer_3, s_question, s_category);

                searchExisableCategory(s_category, new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        DatabaseReference newOrOldCategory = (DatabaseReference) arg;

                        DatabaseReference newTitle = newOrOldCategory.child("titles").push();
                        newTitle.setValue(levelTitle);

                        mLevelsRef.child(newTitle.getKey()).push().setValue(levelData).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_SHORT).show();
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
        });
    }

    private void searchExisableCategory(final String categoryName, final Observer listener) {
        mLevelsInfoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            private DatabaseReference createCategory() {
                DatabaseReference newOrOldCategory = mLevelsInfoRef.push();
                newOrOldCategory.child("category").setValue(categoryName);

                return newOrOldCategory;
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    listener.update(null, createCategory());

                    return;
                }
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                Iterator<String> keys = map.keySet().iterator();
                Iterator<Object> values = map.values().iterator();

                while (keys.hasNext() && values.hasNext()) {
                    String key = keys.next();
                    Map<String, Object> vals = (Map<String, Object>) values.next();

                    if (vals.containsValue(categoryName)) {
                        listener.update(null, mLevelsInfoRef.child(key));

                        return;
                    }
                }

                listener.update(null, createCategory());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
