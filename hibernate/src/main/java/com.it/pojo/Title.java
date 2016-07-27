package com.it.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contextid",unique = true)
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


}
