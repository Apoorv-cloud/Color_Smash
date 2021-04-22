package com.example.colorsmash;

import java.util.ArrayList;

public class User {
    private String username;
    private String name;
    private String highscore;
    private String colorblind;
    private int age;
    private ArrayList<String>scores;
    private ArrayList<String>  badColors;

    public User() {
    }

    public User(String username, String name, int age, String gender,ArrayList<String> scores, ArrayList<String>  badColors) {
        this.scores = (ArrayList<String>) scores.clone();
        this.badColors = (ArrayList<String>) badColors.clone();
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }

    public ArrayList<String> getBadColors() {
        return badColors;
    }

    public void setBadColors(ArrayList<String> badColors) {
        this.badColors = badColors;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public String getHighscore() {
        return highscore;
    }

    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }

    public String getColorblind() {
        return colorblind;
    }

    public void setColorblind(String colorblind) {
        this.colorblind = colorblind;
    }
}
