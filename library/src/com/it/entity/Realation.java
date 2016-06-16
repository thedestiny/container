package com.it.entity;

public class Realation {
    private String ccode;
    private String user;
    private String bcode;
    private String title;
    private String author;
    private String press;
    private String station;
    private String brtime;
    private String retime;
    private Integer bday;

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getBrtime() {
        return brtime;
    }

    public void setBrtime(String brtime) {
        this.brtime = brtime;
    }

    public String getRetime() {
        return retime;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }

    public Integer getBday() {
        return bday;
    }

    public void setBday(Integer bday) {
        this.bday = bday;
    }

    @Override
    public String toString() {
        return "Realation [ccode=" + ccode + ", user=" + user + ", bcode=" + bcode + ", title=" + title + ", author="
                + author + ", press=" + press + ", station=" + station + ", brtime=" + brtime + ", retime=" + retime
                + ", bday=" + bday + "]";
    }


}
