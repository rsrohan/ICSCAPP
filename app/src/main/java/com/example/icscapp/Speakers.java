package com.example.icscapp;

public class Speakers {

    String image, name, about, from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Speakers(){

    }

    public Speakers(String image, String name, String from, String about) {
        this.image = image;
        this.name = name;
        this.about = about;
        this.from = from;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
