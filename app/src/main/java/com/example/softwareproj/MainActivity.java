package com.example.softwareproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth  firebaseAuth;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    /// regular price updates
    private Button updateprice;
    private TextView dailyprice;

    private Button registerButton;
    private EditText editEmail;
    private EditText enterPassword;
    private TextView alreadyReg;
    private ProgressBar progBar;
    private ProgressDialog progDialog;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText userName, userArea, NID, contact;
    private String userType = "Buyer";
    public static String type="Seller";
    public static String userContact="01708521763";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        if(firebaseAuth.getCurrentUser() != null){
            //go to profile
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }

        progBar = new ProgressBar(this);

        progDialog = new ProgressDialog(this);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        userName = (EditText) findViewById(R.id.userName);
        userArea = (EditText) findViewById(R.id.userArea);
        NID = (EditText) findViewById(R.id.NID);
        contact = (EditText) findViewById(R.id.contact);

        registerButton = (Button) findViewById(R.id.registerButton);
        editEmail = (EditText) findViewById(R.id.editEmail);
        enterPassword = (EditText) findViewById(R.id.enterPassword);
        alreadyReg = (TextView) findViewById(R.id.alreadyReg);



        /// regular price updates initialization
        dailyprice = (TextView) findViewById(R.id.textViewDailyPrice);
        updateprice = (Button) findViewById(R.id.updatePrice);

        registerButton.setOnClickListener(this);
        alreadyReg.setOnClickListener(this);
    }






    private void registerUSer(){

        String email = editEmail.getText().toString().trim();
        String password  = enterPassword.getText().toString().trim();
        userContact = contact.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            return;
        }

        progDialog.setMessage("Registering...");
        progDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                            saveUserInfo();
                            finish();
                            if(userType.equals("Seller")){
                                startActivity(new Intent(getApplicationContext(), Profile.class));
                                type=userType;
                            }
                            else{
                                startActivity(new Intent(getApplicationContext(), Profile.class));
                                type=userType;
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Couldn't Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    private void saveUserInfo(){
        String name = userName.getText().toString().trim();
        String area = userArea.getText().toString().trim();
        String nid = NID.getText().toString().trim();
        String cont = contact.getText().toString().trim();

        SaveUserInfo userInfo = new SaveUserInfo(name, area, nid, cont);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String unqId = user.getUid();


        databaseReference.child(userType).child(unqId).setValue(userInfo);
        Toast.makeText(this, "Info Saved!!", Toast.LENGTH_SHORT).show();
    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "You're signing up as a:"+ radioButton.getText(), Toast.LENGTH_SHORT).show();

        userType = radioButton.getText().toString();
        type = userType;
    }


    @Override
    public void onClick(View view) {

        if(view == registerButton){
            registerUSer();
        }

        if(view == alreadyReg){
            startActivity(new Intent(this, Login.class));
        }
    }

    public void showWeb() { startActivity(new Intent(this, UpdatePrice.class));}

}
