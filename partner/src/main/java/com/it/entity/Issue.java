package com.it.entity;

/**
 * Created by xieyue on 2016/6/25.
 * Issue
 * 存放question和answer
 */
public class Issue {

    private Integer id;
    private String questioner;
    private String question;
    private String answer;
    private Integer like;
    private Integer right;
    private Integer skim;
    private String time;

    public Issue() {
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getSkim() {
        return skim;
    }

    public void setSkim(Integer skim) {
        this.skim = skim;
    }
}
