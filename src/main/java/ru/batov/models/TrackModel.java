package ru.batov.models;

public class TrackModel {
    String humanStatus;
    String date;

    public String getHumanStatus() {
        return humanStatus;
    }

    public void setHumanStatus(String humanStatus) {
        this.humanStatus = humanStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TrackModel(String humanStatus, String date) {
        this.humanStatus = humanStatus;
        this.date = date;
    }
}
