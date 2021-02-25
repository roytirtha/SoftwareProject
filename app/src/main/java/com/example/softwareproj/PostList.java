package com.example.softwareproj;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/// need to be okay the reference name 'Post' as post class

public class PostList extends ArrayAdapter<Post> {

    private Activity context;
    private ArrayList<Post>postList;

    public PostList(Activity context, ArrayList<Post>postList){
        super(context, R.layout.postlist_layout,postList);
        this.context = context;
        this.postList = postList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.postlist_layout,null,true);
        TextView textViewUserName = (TextView) listViewItem.findViewById(R.id.textViewUserName);
        TextView textViewUserType = (TextView) listViewItem.findViewById(R.id.textViewUserType);
        TextView textViewQuantity = (TextView) listViewItem.findViewById(R.id.textViewQuantity);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewArea = (TextView) listViewItem.findViewById(R.id.textViewArea);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        Post post = postList.get(position);

        /// Post user name hbe . thik korte hbe
        textViewUserName.setText(post.getUserName());
        textViewUserType.setText(post.getUserType());
        textViewQuantity.setText(post.getQuantity());
        textViewPrice.setText(post.getPrice());
        textViewArea.setText(post.getArea());

        textViewDescription.setText((CharSequence) post.getDescription());
        return listViewItem;
    }
}
