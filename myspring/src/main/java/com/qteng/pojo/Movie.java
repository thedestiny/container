package com.qteng.pojo;

/**
 * Created by xieyue on 2016/6/17.
 * Movie
 */
public class Movie {

    private Integer id ;
    private String film;
    private Float  rate;
    private String issue;
    private String screen;
    private String director;
    private String summary;

    public Movie() {
    }

    public Movie(String film, Float rate, String issue, String screen, String director, String summary) {
        this.film = film;
        this.rate = rate;
        this.issue = issue;
        this.screen = screen;
        this.director = director;
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

}
