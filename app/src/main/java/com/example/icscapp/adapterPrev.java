package com.example.icscapp;

public class adapterPrev {
    String year;
    String info;
    String image;

    public  adapterPrev(){

    }

   public adapterPrev(String year, String info, String image){
        this.year = year;
        this.info = info;
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
