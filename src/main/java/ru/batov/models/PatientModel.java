package ru.batov.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientModel {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ProtocolNumber")
    @Expose
    private String protocolNumber;
    @SerializedName("ExamBuroName")
    @Expose
    private String examBuroName;
    @SerializedName("DecisionDate")
    @Expose
    private String decisionDate;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("SecondName")
    @Expose
    private String secondName;
    @SerializedName("PurposesXml")
    @Expose
    private String purposesXml;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getExamBuroName() {
        return examBuroName;
    }

    public void setExamBuroName(String examBuroName) {
        this.examBuroName = examBuroName;
    }

    public String getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(String decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPurposesXml() {
        return purposesXml;
    }

    public void setPurposesXml(String purposesXml) {
        this.purposesXml = purposesXml;
    }

}
