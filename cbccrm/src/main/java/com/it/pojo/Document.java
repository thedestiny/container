package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/7/12.
 * Document
 */


public class Document implements Serializable {

    private static final long serialVersionUID = -9004151417293252381L;
    private Integer id;
    private Integer userid;
    private String filename;
    private String savename;
    private String createtime;
    private Boolean type;
    private Long filesize;
    private Integer faid;
    private String md5;
    private String realname;
    private String formatsize;
    private String suffix;
    private String contexttype;

    public String getContexttype() {
        return contexttype;
    }

    public void setContexttype(String contexttype) {
        this.contexttype = contexttype;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Document() {
    }

    public Document(Integer id) {
        this.id = id;
    }


    public Document(String filename) {
        this.filename = filename;
        this.type = false;
    }

    public Document(String filename, Boolean type) {
        this.filename = filename;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public Integer getFaid() {
        return faid;
    }

    public void setFaid(Integer faid) {
        this.faid = faid;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFormatsize() {
        return formatsize;
    }

    public void setFormatsize(String formatsize) {
        this.formatsize = formatsize;
    }
}
