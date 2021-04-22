package com.example.colorsmash;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameFunctionalityActivity extends AppCompatActivity {

    class Player{
        String name;
        int location;
        boolean gameover;
        double blindColor;
        int score;

        Player(){
            name="None";
            location=50;
            gameover=false;
            blindColor = 0.5;
            score=0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public boolean isGameOver() {
            return gameover;
        }

        public void setGameOver(boolean gameover) {
            this.gameover = gameover;
        }

        public double getBlindColor() {
            return blindColor;
        }

        public void setBlindColor(double blindColor) {
            this.blindColor = blindColor;
        }

        public boolean isGameover() {
            return gameover;
        }

        public void setGameover(boolean gameover) {
            this.gameover = gameover;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_functionality);
    }

    public void endGame() {
        player.setGameOver(true);
    }

    public void startGame()
    {
        player.setGameOver(false);
    }

    public boolean checkGameOver(){

        return player.isGameOver();
    }

    public int checkPlayerLocation()
    {
        return player.getLocation();
    }

    public double calcProbabilityOfBlindColors()
    {
        return player.getBlindColor();
    }

    public int returnPlayerScore()
    {
        return player.getScore();
    }

    public void initializeScore()
    {
        player.setScore(0);
    }

    public void initializeLocation()
    {
        player.setLocation(0);
    }

    public void setScore(int sc){

        player.setScore(sc);
    }

    public int getLocation(){
        return player.getLocation();
    }
    public void setLocation(int loc){
        player.setLocation(loc);
    }
}
