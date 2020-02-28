package com.jiit.icscapp;

public class committee_class {

    String name;
    String desg;

    committee_class(){

    }
    committee_class(String name, String desg){
        this.name=name;
        this.desg=desg;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdesg() {
        return desg;
    }

    public void setdesg(String desg) {
        this.desg = desg;
    }
}
