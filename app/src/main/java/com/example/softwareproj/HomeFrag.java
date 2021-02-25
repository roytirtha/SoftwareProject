package com.example.softwareproj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFrag extends Fragment {

    private FirebaseAuth  firebaseAuth;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    ///listview for post
    private ListView listViewPost;

    /// post list added
    ArrayList<Post> postlist = new ArrayList<Post>();
    PostList postAdapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listViewPost = (ListView) view.findViewById(R.id.listViewPost);
        //ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(inflater.inflate(R.layout.postlist_layout,null,true));
        return view;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("All Posts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String username = dataSnapshot1.child("postUserName").getValue().toString();
                    String usrType = dataSnapshot1.child("postType").getValue().toString();
                    String quantity = dataSnapshot1.child("postQuant").getValue().toString();
                    String price = dataSnapshot1.child("postPrc").getValue().toString();
                    String address = dataSnapshot1.child("postAdd").getValue().toString();
                    String contact = MainActivity.userContact;
                    String description = dataSnapshot1.child("postProd").getValue().toString();

                    Post post = new Post(username, usrType, quantity,price, address, description);

                   // Post post = dataSnapshot1.getValue(Post.class);
                    postlist.add(post);
                }

                postAdapter = new PostList(getActivity(),postlist);
                listViewPost.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), "Opsss...Something is wrong.", Toast.LENGTH_SHORT).show();

            }
        });

        //list view post initialization
      //  listViewPost = (ListView) View.findViewById(R.id.listViewPost);


        /// post list initialization

//        postlist.add("hello there");
//        postlist.add("hi there");
//        postlist.add("there");

//        postAdapter = new PostList(getActivity(), postlist);
//        listViewPost.setAdapter(postAdapter);
    }




    /// database reference added for fetching post from database by mesbah
//    private MutableLiveData<String> userNameMutableLiveData = new MutableLiveData<>();
//
//    public MutableLiveData<String> getUserName(String uid) {
//
//        databaseReference.child(String.format("users/%s/name", uid))
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // How to return this value?
//                        String userName = dataSnapshot.getValue(String.class);
//                        userNameMutableLiveData.setValue(userName);
//                        postlist.add(userName);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {}
//                });
//
//        return userNameMutableLiveData;
//    }

}
