package com.example.quizar;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LevelInfo {
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mInfoRef = mRootRef.child("levels_info");

    public static ArrayList<String> idLvls = new ArrayList<>();

    public static DatabaseReference getmInfoRef() {
        getmInfoRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot idlvl : dataSnapshot.getChildren()){
                    idLvls.add(idlvl.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return getmInfoRef();
    }
}
