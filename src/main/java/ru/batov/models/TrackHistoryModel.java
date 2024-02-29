package ru.batov.models;

public class TrackHistoryModel {
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

    public TrackHistoryModel(String humanStatus, String date) {
        this.humanStatus = humanStatus;
        this.date = date;
    }
}
