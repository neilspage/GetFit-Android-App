package com.example.michelle.useraccount.tabs;

import com.example.michelle.useraccount.WorkoutFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContentManager {

    /**
     * An array of sample (dummy) items.
     */

    public static final List<WorkoutItem> ITEMS = new ArrayList<WorkoutItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, WorkoutItem> ITEM_MAP = new HashMap<String, WorkoutItem>();


    static {
        // Add some sample items.
        int id = 0;
        for(WorkoutItem item : WorkoutFetcher.getWorkouts()){
            id++;
            addItem(createWorkoutItem(id, item.getName(), item.getLink()));
        }
    }


    private static void addItem(WorkoutItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static WorkoutItem createWorkoutItem(int position, String content, String details) {
        return new WorkoutItem("Workout: "+String.valueOf(position), content, details);
    }

    public static String getUrlByNameOfWorkout(String name) {
        String url="NA";
        for(WorkoutItem item : WorkoutFetcher.getWorkouts()){
            if(item.getName().equals(name)){
                url = item.getLink();
            }
        }
        return url;
    }

    public static String getWorkoutByUrlName(String currentWorkoutUrl) {
        String workoutName="NA";
        for(WorkoutItem item : WorkoutFetcher.getWorkouts()){
            if(item.getLink().equals(currentWorkoutUrl)){
                workoutName = item.getName();
            }
        }
        return workoutName;
    }

    /*private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }*/

    /**
     * A dummy item representing a piece of content.
     */

    public static class WorkoutItem {
        public String id, content, details;
        public WorkoutItem(String id, String content, String details) {
            this.content = content;
            this.details = details;
            this.id = id;
        }

        public String getName() {
            return content;
        }

        public String getLink() {
            return details;
        }
        @Override
        public String toString() {
            return content;
        }
    }
}
