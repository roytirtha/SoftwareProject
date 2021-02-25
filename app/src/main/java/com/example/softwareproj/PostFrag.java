package com.example.softwareproj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostFrag extends Fragment implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;

    private Button post;
    private TextView userNID;

    private EditText product, quantity, price, area, userType;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    public PostFrag() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        post = (Button) view.findViewById(R.id.post);
        userNID = (TextView)view.findViewById(R.id.NID);
        product = (EditText)view.findViewById(R.id.product);
        quantity = (EditText)view.findViewById(R.id.quantity);
        price = (EditText)view.findViewById(R.id.price);
        area = (EditText)view.findViewById(R.id.area);
        userType = (EditText)view.findViewById(R.id.userType);

        post.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //FirebaseUser user = firebaseAuth.getCurrentUser();
        //userEmail.setText("Welcome " + user.getEmail());
        //final String unqId = user.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String userName = dataSnapshot.child(MainActivity.type).child(Profile.userUnqID).child("name").getValue().toString();

                    //name = userName;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void post(){
        String prod = product.getText().toString().trim();
        String quant = quantity.getText().toString().trim();
        String prc = price.getText().toString().trim();
        String add = area.getText().toString().trim();
        String type = userType.getText().toString().trim();
        final String name = "Tirtha";


       // FirebaseUser user = firebaseAuth.getCurrentUser();
        //userEmail.setText("Welcome " + user.getEmail());
        //final String unqId = user.getUid();


        ProductPost post = new ProductPost(prod, quant, prc, add, type, name);
        databaseReference.child("All Posts").child(Profile.userUnqID).setValue(post);
        //Toast.makeText(getApplicationContext(), "Posted", LENGTH_SHORT).show();


    }

    public PostFrag(TextView userNID, EditText product, EditText quantity, EditText price, EditText area, EditText userType) {
        this.userNID = userNID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.area = area;
        this.userType = userType;
    }

    public String getUserNID() {
        return String.valueOf(userNID);
    }

    public String getProduct() {
        return String.valueOf(product);
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public String getArea() {
        return String.valueOf(area);
    }

    public String getUserType() {
        return String.valueOf(userType);
    }

    @Override
    public void onClick(View v) {
        if(v == post){
            post();
        }
    }


}
