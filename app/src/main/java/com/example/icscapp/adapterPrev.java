package com.example.icscapp;

public class adapterPrev {
    Long year;
    String info;
    String img;

    public  adapterPrev(){

    }

   public adapterPrev(Long year, String info, String img){
        this.year = year;
        this.info = info;
        this.img = img;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
