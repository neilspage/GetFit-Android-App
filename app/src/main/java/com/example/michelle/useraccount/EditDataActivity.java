package com.example.michelle.useraccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private Button buttonSave, buttonDelete;
    private EditText editable_item, editable_item2, editable_item3, editable_item4, editable_item5, editable_item6;
    DatabaseHelper mDatabaseHelper;
    private String selectedFoodActivity;
    private int selectedCarbohydrates;
    private int selectedFat;
    private int selectedProtein;
    private int selectedSugar;
    private int selectedEnergy;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_nutrition_data_layout);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        editable_item2 = (EditText) findViewById(R.id.editable_item2);
        editable_item3 = (EditText) findViewById(R.id.editable_item3);
        editable_item4 = (EditText) findViewById(R.id.editable_item4);
        editable_item5 = (EditText) findViewById(R.id.editable_item5);
        editable_item6 = (EditText) findViewById(R.id.editable_item6);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedFoodActivity = receivedIntent.getStringExtra("foodActivity");
        selectedCarbohydrates = receivedIntent.getIntExtra("carbohydrates", 0);
        selectedFat = receivedIntent.getIntExtra("fat", 0);
        selectedProtein = receivedIntent.getIntExtra("protein", 0);
        selectedSugar = receivedIntent.getIntExtra("sugar", 0);
        selectedEnergy = receivedIntent.getIntExtra("energy", 0);
        editable_item.setText(selectedFoodActivity);
        editable_item2.setText(String.valueOf(selectedCarbohydrates));
        editable_item3.setText(String.valueOf(selectedFat));
        editable_item4.setText(String.valueOf(selectedProtein));
        editable_item5.setText(String.valueOf(selectedSugar));
        editable_item6.setText(String.valueOf(selectedEnergy));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                int carbohydrates = Integer.parseInt(editable_item2.getText().toString());
                int fat = Integer.parseInt(editable_item3.getText().toString());
                int protein = Integer.parseInt(editable_item4.getText().toString());
                int sugar = Integer.parseInt(editable_item5.getText().toString());
                int energy = Integer.parseInt(editable_item6.getText().toString());
                if(!item.equals("")) {
                    mDatabaseHelper.updateFoodActivity(
                            item, selectedID, selectedFoodActivity,
                            carbohydrates, selectedCarbohydrates,
                            fat, selectedFat,
                            protein, selectedProtein,
                            sugar, selectedSugar,
                            energy, selectedEnergy);
                    toastMessage("Changes saved");
                } else {
                    toastMessage("You must enter a food or activity");
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteFoodActivity(selectedID, selectedFoodActivity);
                startActivity(new Intent(EditDataActivity.this, ListDataActivity.class));
                finish();
                toastMessage("Removed from database");
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
