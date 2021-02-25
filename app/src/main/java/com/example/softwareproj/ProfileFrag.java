package com.example.softwareproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFrag extends Fragment implements View.OnClickListener{


    private FirebaseAuth firebaseAuth;

    private TextView userEmail;
    private Button logOutButton, saveInfo;
    private TextView userNID;
    private TextView userName;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static String userUnqID;
    public String TAG="Logging out";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logOutButton = (Button) view.findViewById(R.id.logOutButton);
        userNID = (TextView)view.findViewById(R.id.NID);
        userName = (TextView)view.findViewById(R.id.Name);

        logOutButton.setOnClickListener(this);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        showUserInfo();

        /*if(firebaseAuth.getCurrentUser() == null){
            //go to profile
            //finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }*/

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //userEmail.setText("Welcome " + user.getEmail());
        final String unqId = user.getUid();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String NID = dataSnapshot.child(MainActivity.type).child(unqId).child("nid").getValue().toString();
                    String name = dataSnapshot.child(MainActivity.type).child(unqId).child("name").getValue().toString();

                    userNID.setText(NID);
                    userName.setText(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onStart() {

        super.onStart();
    }
    private void showUserInfo() {

        FirebaseUser user = firebaseAuth.getCurrentUser();

        String uID = user.getUid();
        userUnqID = user.getUid();
    }

    public void onClick(View view) {
        if(view == logOutButton){
            firebaseAuth.signOut();
            //finish();
            Log.d(TAG,"");
            Toast.makeText(getContext(),"Logging out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(view.getContext(), Login.class));
        }

        //if(view == saveInfo){
          //  saveUserInfo();
        //}

    }
//    public void logOutClick(){
//        firebaseAuth.signOut();
//        //finish();
//        Log.d(TAG,"");
//        Toast.makeText(getContext(),"Logging out", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getContext(), Login.class));
//    }

}