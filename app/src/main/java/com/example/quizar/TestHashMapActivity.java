package com.example.quizar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;

import java.util.Map;

public class TestHashMapActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mLevelRef = mRootRef.child("levels_data");
    private DatabaseReference mCategoryRef = mRootRef.child("levels_category");
    private DatabaseReference mTittleRef = mRootRef.child("levels_tittle");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_hash_map);

        mRootRef.child("levels_data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();

                for (Object key : data.keySet()) {
                    Map<String, Object> datas = (Map<String, Object>) ((Map<String, Object>) data.get(key)).get("titles");

                    for (Object level : datas.keySet()) {
                        LevelData levelData = CustomClassMapper.convertToCustomClass(datas.get(level), LevelData.class);

                        System.out.println(levelData);
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
