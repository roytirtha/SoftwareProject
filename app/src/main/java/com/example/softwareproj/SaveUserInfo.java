package com.example.softwareproj;

public class SaveUserInfo {

    public String name, area, nid, cont;

    public SaveUserInfo(String name, String area){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public SaveUserInfo(String name, String area, String nid, String cont){
        this.name = name;
        this.area = area;
        this.nid = nid;
        this.cont = cont;

    }
}
