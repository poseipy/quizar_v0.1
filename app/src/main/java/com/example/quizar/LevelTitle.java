package com.example.quizar;

public class LevelTitle {
    private String level_name;
    private String S_selectedLevel;
    private String levelId;
    private String dataSnapshot;

    public LevelTitle(){}

    public LevelTitle(String level_name, String S_selectedLevel, String levelId, String dataSnapshot){
        this.level_name = level_name;
        this.S_selectedLevel = S_selectedLevel;
        this.levelId = levelId;
        this.dataSnapshot = dataSnapshot;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getS_selectedLevel() {
        return S_selectedLevel;
    }

    public void setS_selectedLevel(String s_selectedLevel) {
        S_selectedLevel = s_selectedLevel;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getDataSnapshot() {
        return dataSnapshot;
    }

    public void setDataSnapshot(String dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }
}

