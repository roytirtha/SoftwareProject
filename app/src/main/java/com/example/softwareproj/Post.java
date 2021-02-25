package com.example.softwareproj;

import android.util.Log;

public class Post {

    private String userName;
    private String userType;
    private String Quantity;
    private String Price;
    private String Area;
    private String Description;
    private String Contact;
    public String TAG = "Soft";

    public Post(){

    }


    public Post(String userName, String userType, String quantity, String price, String area, String description) {
        this.userName = userName;
        this.userType = userType;
        Quantity = quantity;
        Price = price;
        Area = area;
        Description = description;
        Log.d(TAG, " "+userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
