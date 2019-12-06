package com.intranet.onlineshop.domain.models.service;

public class PostOfficeConnectionModel {

    private String firstPostOfficeName;
    private String secondPostOfficeName;
    private int distance;

    public PostOfficeConnectionModel() {
    }

    public String getFirstPostOfficeName() {
        return firstPostOfficeName;
    }

    public void setFirstPostOfficeName(String firstPostOfficeName) {
        this.firstPostOfficeName = firstPostOfficeName;
    }

    public String getSecondPostOfficeName() {
        return secondPostOfficeName;
    }

    public void setSecondPostOfficeName(String secondPostOfficeName) {
        this.secondPostOfficeName = secondPostOfficeName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
