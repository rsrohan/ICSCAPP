package com.jiit.icscapp;

public class Schedules {
    String paperid, papername, timing, otherinfo;

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public Schedules(){}

    public Schedules(String paperid, String papername, String timing, String otherinfo) {
        this.paperid = paperid;
        this.papername = papername;
        this.timing = timing;
        this.otherinfo = otherinfo;
    }
}
