package com.example.quizar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KeyData {
    String academyCategoryKey;
    String gameCategoryKey;
    String triviaCategoryKey;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mInfoRef = mRootRef.child("levels_info");

    public KeyData(){}

    public KeyData(String academyCategoryKey, String gameCategoryKey, String triviaCategoryKey){
        this.academyCategoryKey = academyCategoryKey;
        this.gameCategoryKey = gameCategoryKey;
        this.triviaCategoryKey = triviaCategoryKey;

    }
    

    public String getAcademyCategoryKey() {
        return academyCategoryKey;
    }

    public void setAcademyCategoryKey(String academyCategoryKey) {
        this.academyCategoryKey = academyCategoryKey;
    }

    public String getGameCategoryKey() {
        return gameCategoryKey;
    }

    public void setGameCategoryKey(String gameCategoryKey) {
        this.gameCategoryKey = gameCategoryKey;
    }

    public String getTriviaCategoryKey() {
        return triviaCategoryKey;
    }

    public void setTriviaCategoryKey(String triviaCategoryKey) {
        this.triviaCategoryKey = triviaCategoryKey;
    }
}

