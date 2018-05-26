package com.example.michelle.useraccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    ImageButton buttonProfile, buttonNutritionTracker, buttonPedometer, buttonWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        buttonProfile = (ImageButton) findViewById(R.id.buttonProfile);
        buttonNutritionTracker = (ImageButton) findViewById(R.id.buttonNutritionTracker);
        buttonPedometer = (ImageButton) findViewById(R.id.buttonPedometer);
        buttonWorkout = (ImageButton) findViewById(R.id.buttonWorkout);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
    }
}
