package com.example.softwareproj;

public class ProductPost {

    public String postProd;
    public String postQuant;
    public String postPrc;
    public String postAdd;
    public String postType;
    public String postUserName;
    public String postContact;

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getProd() {
        return postProd;
    }

    public void setProd(String prod) {
        this.postProd = prod;
    }

    public String getQuant() {
        return postQuant;
    }

    public void setQuant(String quant) {
        this.postQuant = quant;
    }

    public String getPrc() {
        return postPrc;
    }

    public void setPrc(String prc) {
        this.postPrc = prc;
    }

    public String getAdd() {
        return postAdd;
    }

    public void setAdd(String add) {
        this.postAdd = add;
    }

    public String getType() {
        return postType;
    }

    public void setType(String type) {
        this.postType =type;
    }



    public ProductPost(String prod, String quant, String prc, String add, String type, String postUserName){
        this.postProd=prod;
        this.postQuant=quant;
        this.postPrc=prc;
        this.postAdd=add;
        this.postType=type;
        this.postUserName=postUserName;
    }
}

