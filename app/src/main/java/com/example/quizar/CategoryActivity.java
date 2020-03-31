package com.example.quizar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;

import java.util.ArrayList;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mLevelRef = mRootRef.child("levels_data");
    private DatabaseReference mInfoRef = mRootRef.child("levels_info");
    private DatabaseReference mCategoryRef = mRootRef.child("levels_category");
    private DatabaseReference mTittleRef = mRootRef.child("levels_tittle");



    private Button playButton;
    private TextView TxtView;
    String categoryGameKey;
    String categoryAcademicKey;
    String level_name;
    TextView view;
    String selectedLevel;

    ArrayList<String> idC = new ArrayList<>();
    ArrayList<String> idLvl = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
//      TxtView = findViewById(R.id.textView);
        view = findViewById(R.id.testView);
        playButton = findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   System.out.println(LevelInfo.idLvls);
            }
        });


        mInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot idCategory : dataSnapshot.getChildren()) {
                    idC.add(idCategory.getKey());
                    for (DataSnapshot idCategory1 : dataSnapshot.getChildren()) {
                        idC.add(idCategory1.getKey());
                    }
                    //System.out.println(idC);
                    int i = 0;
                    final String categoryGame = idC.get(i);
                    final String categoryAcaademic = idC.get(++i);


                    mInfoRef.child(categoryGame).child("titles").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot idLevels : dataSnapshot.getChildren()){
                                idLvl.add(idLevels.getKey());
                                playButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                      //  System.out.println(categoryAcaademic);
                                      //  System.out.println(categoryGame);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRootRef.child("levels_info").orderByChild("category").equalTo("Academic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue( );

                for (Object key : data.keySet()) {
                    Map<String, Object> tittleKey = (Map<String, Object>) ((Map<String, Object>) data.get(key)).get("titles");

                    for (Object title : tittleKey.keySet()) {
                        LevelTitle levelTitle = CustomClassMapper.convertToCustomClass(tittleKey.get(title), LevelTitle.class);

//                        System.out.println(levelTitle.getLevel_name());
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
