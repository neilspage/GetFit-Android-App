package com.example.hubert.userprofilewithauthentication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddtoDatabase extends AppCompatActivity{

    private static final String TAG = "AddToDatabase";
    private Button mAddToDB;
    private EditText nNewName;
    private EditText nPhone;
    private EditText nWeight;
    private EditText nHeight;
    private EditText nGoal;

    //add Firebase
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_database);
        mAddToDB = (Button) findViewById(R.id.btnAddNewName);
        nNewName = (EditText) findViewById(R.id.add_name);
        nPhone = (EditText) findViewById(R.id.add_phone);
        nWeight = (EditText) findViewById(R.id.add_weight);
        nHeight = (EditText) findViewById(R.id.add_height);
        nGoal = (EditText) findViewById(R.id.add_goal);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mAddToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Attempting to add object to database.");
                String newName = nNewName.getText().toString();
                String newPhone = nPhone.getText().toString();
                String newWeight = nWeight.getText().toString();
                String newHeight = nHeight.getText().toString();
                String newGoal = nGoal.getText().toString();
                if(!newName.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("User").child(userID).child("Name").setValue(newName);
                    toastMessage("Adding " + newName + " to database...");
                    //reset the text
                    nNewName.setText("");
                }
                if(!newPhone.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("User").child(userID).child("Phone").setValue(newPhone);
                    toastMessage("Adding " + newPhone + " to database...");
                    //reset the text
                    nPhone.setText("");
                }
                if(!newWeight.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("User").child(userID).child("Weight").setValue(newWeight);
                    toastMessage("Adding " + newWeight + " to database...");
                    //reset the text
                    nWeight.setText("");
                }
                if(!newHeight.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("User").child(userID).child("Height").setValue(newHeight);
                    toastMessage("Adding " + newHeight + " to database...");
                    //reset the text
                    nHeight.setText("");
                }
                if(!newGoal.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("User").child(userID).child("Goal").setValue(newGoal);
                    toastMessage("Adding " + newGoal + " to database...");
                    //reset the text
                    nGoal.setText("");
                }
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
