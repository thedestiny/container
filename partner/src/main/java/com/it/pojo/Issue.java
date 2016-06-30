package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/6/25.
 * Issue
 * 存放question和answer
 */
public class Issue implements Serializable {

    private static final long serialVersionUID = -1094641852091631059L;
    private Integer id;
    private String questioner;
    private String question;
    private Integer answer;
    private Integer like;
    private Integer right;
    private Integer skim;
    private String time;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Issue() {
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", questioner='" + questioner + '\'' +
                ", question='" + question + '\'' +
                ", answer=" + answer +
                ", like=" + like +
                ", right=" + right +
                ", skim=" + skim +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
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
