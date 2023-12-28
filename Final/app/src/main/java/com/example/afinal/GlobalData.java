package com.example.afinal;

public class GlobalData {
    public static int score = 0;

    public static void increaseEasy() {
        score++;
    }

    public static void increaseHard() {
        score+=2;
    }

    public static void resetScore(){
        score=0;
    }
}
