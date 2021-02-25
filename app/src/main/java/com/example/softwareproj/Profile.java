package com.example.softwareproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView userEmail, userNID;
    private Button logOutButton, saveInfo;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText userName, userArea;
    private EditText welcomeUser;
    public static String userUnqID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userEmail = (TextView) findViewById(R.id.welcomeUser);
        //logOutButton = (Button) findViewById(R.id.logOut);
        userName = (EditText) findViewById(R.id.userName);
        userArea = (EditText) findViewById(R.id.userArea);
        userNID = (TextView) findViewById(R.id.nid);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

//        logOutButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        if(firebaseAuth.getCurrentUser() == null){
            //go to profile
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //userEmail.setText("Welcome " + user.getEmail());
        final String unqId = user.getUid();
        userUnqID = unqId;

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String NID = dataSnapshot.child(MainActivity.type).child(unqId).child("nid").getValue().toString();

                    userNID.setText(NID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFrag();
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFrag();
                            break;
                        case R.id.nav_post:
                            selectedFragment = new PostFrag();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

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
