package com.example.michelle.useraccount;

/**
 * Created by Neil on 11-May-18.
 */
public class CurrentWorkout {
    private static String currentWorkout = ""; //current workout as a string form of URL
    public static String getCurrentWorkout(){
        return currentWorkout;
    }

    public static void setCurrentWorkout(String currentWorkout) {
        CurrentWorkout.currentWorkout = currentWorkout;
    }
}
