package com.example.quizar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;

import java.util.Map;

public class CategoryActivity extends AppCompatActivity {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mLevelRef = mRootRef.child("levels_data");
    private DatabaseReference mCategoryRef = mRootRef.child("levels_category");
    private DatabaseReference mTittleRef = mRootRef.child("levels_tittle");

    private TextView TxtView;
    String category;
    String level_name;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
//        TxtView = findViewById(R.id.textView);
        view = findViewById(R.id.testView);

        String id = mRootRef.child("levels_info").getKey();

        mRootRef.child("levels_info").orderByChild("category").equalTo("Game").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();

                for (Object key : data.keySet()) {
                    Map<String, Object> titles = (Map<String, Object>) ((Map<String, Object>) data.get(key)).get("titles");

                    for (Object title : titles.keySet()) {
                        LevelTitle levelTitle = CustomClassMapper.convertToCustomClass(titles.get(title), LevelTitle.class);

                        System.out.println(levelTitle.getLevel_name());
                    }

//                    System.out.println(data.get(key));
//                    CustomClassMapper.convertToCustomClass(data.get(key), )
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
