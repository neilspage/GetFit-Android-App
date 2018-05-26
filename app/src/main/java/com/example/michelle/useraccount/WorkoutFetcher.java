package com.example.michelle.useraccount;

import com.example.michelle.useraccount.tabs.ContentManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WorkoutFetcher {

    private static ArrayList<ContentManager.WorkoutItem> workouts = new ArrayList<ContentManager.WorkoutItem>();

    public static void initialiseWorkouts() throws IOException {
        Scanner scanner = new Scanner(new URL("https://13247390.000webhostapp.com/").openStream());
        String workoutString = "";

        while(scanner.hasNextLine()){
            workoutString+=scanner.nextLine();
        }

        String[] vids = workoutString.split("@ls@");

        for(String vid : vids){
            String[] parts = vid.split("@split@");
            System.out.println(parts[1] + "|" + parts[2]); //intentionally ignoring element[0] (the first array index).
            workouts.add(new ContentManager.WorkoutItem("unassigned",parts[1],parts[2]));
        }
        scanner.close();
        Collections.shuffle(workouts);
    }

    public static ArrayList<ContentManager.WorkoutItem> getWorkouts(){
        return workouts;
    }
}
