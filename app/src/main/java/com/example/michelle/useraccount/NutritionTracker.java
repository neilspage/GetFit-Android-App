package com.example.michelle.useraccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NutritionTracker extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button buttonAdd, buttonViewData;
    private EditText editText, editText2, editText3, editText4, editText5, editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tracker);
        editText = (EditText) findViewById(R.id.editText);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonViewData = (Button) findViewById(R.id.buttonViewData);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        mDatabaseHelper = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                int carbohydrates, fat, protein, sugar, energy;

                if (editText2.getText().toString().equals("")) carbohydrates = 0;
                else carbohydrates = Integer.parseInt(editText2.getText().toString());

                if (editText3.getText().toString().equals("")) fat = 0;
                else fat = Integer.parseInt(editText3.getText().toString());

                if (editText4.getText().toString().equals("")) protein = 0;
                else protein = Integer.parseInt(editText4.getText().toString());

                if (editText5.getText().toString().equals("")) sugar = 0;
                else sugar = Integer.parseInt(editText5.getText().toString());

                if (editText6.getText().toString().equals("")) energy = 0;
                else energy = Integer.parseInt(editText6.getText().toString());

                if (editText.length() != 0) {
                    addData(newEntry, carbohydrates, fat, protein, sugar, energy);
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
                    editText6.setText("");
                } else {
                    toastMessage("You must enter a food or activity!");
                }
            }
        });

        buttonViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionTracker.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addData(String newEntry, int carbohydrates, int fat, int protein, int sugar, int energy) {
        boolean insertData = mDatabaseHelper.addData(newEntry, carbohydrates, fat, protein, sugar, energy);

        if (insertData) {
            toastMessage("Data successfully inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
