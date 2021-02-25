package com.example.softwareproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_Buyer extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView userEmail;
    private Button logOutButton, saveInfo;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText userName, userArea;
    private EditText welcomeUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userEmail = (TextView) findViewById(R.id.welcomeUser);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        userName = (EditText) findViewById(R.id.userName);
        userArea = (EditText) findViewById(R.id.userArea);


        logOutButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        if(firebaseAuth.getCurrentUser() == null){
            //go to profile
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userEmail.setText("Welcome " + user.getEmail());
    }


    /*private void saveUserInfo(){
        String name = userName.getText().toString().trim();
        String area = userArea.getText().toString().trim();

        SaveUserInfo userInfo = new SaveUserInfo(name, area);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String unqId = user.getUid();


        databaseReference.child("Seller").child(unqId).setValue(userInfo);
        Toast.makeText(this, "Info Saved!!", Toast.LENGTH_SHORT).show();
    }*/



    @Override
    public void onClick(View view) {
        if(view == logOutButton){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }

        /*if(view == saveInfo){
            saveUserInfo();
        }*/

    }
}
