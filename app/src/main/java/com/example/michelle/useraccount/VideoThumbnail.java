package com.example.michelle.useraccount;

import com.example.michelle.useraccount.tabs.ContentManager;

public class VideoThumbnail {
    public static String getThumbnail(ContentManager.WorkoutItem item){
        String link = item.getLink();
        String videoId = link.substring(link.indexOf("watch?v="),link.length()).replace("watch?v=","").trim();
        return "https://img.youtube.com/vi/@videoid@/0.jpg".replace("@videoid@",videoId);
    }
}
