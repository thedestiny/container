package com.it.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_task")
public class Task implements Serializable {

    private static final long serialVersionUID = -5427250782226730279L;
    @Id
    @GenericGenerator(name="mystyle",strategy = "uuid")
    @GeneratedValue(generator = "mystyle")
    private String id;
    private String content;
    @Version
    private Integer version;
    // 配置文件
//    <version name = "version">

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
