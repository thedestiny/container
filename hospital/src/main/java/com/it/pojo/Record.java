package com.it.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_record")
public class Record implements Serializable {

    private static final long serialVersionUID = 4639151341086286284L;
    @Id
    private String id;
    private String content;
    private String symptom; // 主要症状
    private String positivesign; // 阳性体征
    private String result; // 诊断结果
    private String treatment; // 治疗方案
    private String returntime; // 复诊时间
    private String information; // 影像资料
    private String diseasename; // 病名
    private String username;   // 主治医生
    private String patientname; // 病人姓名
    private String state;
    private String createtime;
    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "diseaseid")
    private Disease disease;

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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getPositivesign() {
        return positivesign;
    }

    public void setPositivesign(String positivesign) {
        this.positivesign = positivesign;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
