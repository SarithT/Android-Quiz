package com.example.david.quiz;

/**
 * Created by david on 26.11.2017.
 */

public class Result {
    private int id;
    private int score;
    private String category;
    private String difficulty;
    private int number;

    public Result(String category, String difficulty, int number, int score) {
        this.score = score;
        this.category = category;
        this.difficulty = difficulty;
        this.number = number;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
