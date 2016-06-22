package com.it.entity;

/**
 * Created by xieyue on 2016/6/22.
 * Film
 */
public class Film {
    private String title;
    private String director;
    private String players;
    private String type;
    private String nation;
    private String screen;
    private Double score;

    /**
     *
     * @param title 影名
     * @param score 评分
     * @param director 导演
     * @param players 演员
     * @param type 类型
     * @param nation 地区
     * @param screen 上映时间
     */
    public Film(String title, Double score, String director, String players,
                String type, String nation, String screen) {
        this.title = title;
        this.score = score;
        this.director = director;
        this.players = players;
        this.type = type;
        this.nation = nation;
        this.screen = screen;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", players='" + players + '\'' +
                ", type='" + type + '\'' +
                ", nation='" + nation + '\'' +
                ", screen='" + screen + '\'' +
                ", score=" + score +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
