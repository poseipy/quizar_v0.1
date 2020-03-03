package com.example.quizar;

public class LevelData {
    private String s_question_image;
    private String s_right_answer;
    private String s_answer_1;
    private String s_answer_2;
    private String s_answer_3;
    private String s_question;

    public LevelData() {}

    public LevelData(String s_question_image, String s_right_answer, String s_answer_1, String s_answer_2, String s_answer_3, String s_question) {
        this.s_question_image = s_question_image;
        this.s_right_answer = s_right_answer;
        this.s_answer_1 = s_answer_1;
        this.s_answer_2 = s_answer_2;
        this.s_answer_3 = s_answer_3;
        this.s_question = s_question;
    }

    public String getS_question_image() {
        return s_question_image;
    }

    public void setS_question_image(String s_question_image) {
        this.s_question_image = s_question_image;
    }

    public String getS_right_answer() {
        return s_right_answer;
    }

    public void setS_right_answer(String s_right_answer) {
        this.s_right_answer = s_right_answer;
    }

    public String getS_answer_1() {
        return s_answer_1;
    }

    public void setS_answer_1(String s_answer_1) {
        this.s_answer_1 = s_answer_1;
    }

    public String getS_answer_2() {
        return s_answer_2;
    }

    public void setS_answer_2(String s_answer_2) {
        this.s_answer_2 = s_answer_2;
    }

    public String getS_answer_3() {
        return s_answer_3;
    }

    public void setS_answer_3(String s_answer_3) {
        this.s_answer_3 = s_answer_3;
    }

    public String getS_question() {
        return s_question;
    }

    public void setS_question(String s_question) {
        this.s_question = s_question;
    }
}
