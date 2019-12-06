package com.intranet.onlineshop.domain.models.service;

public class NodeStateModel {

    private int pathDistance;
    private boolean isUsed;
    private String predecessor;

    public NodeStateModel(int pathDistance, boolean isUsed, String predecessor) {
        this.pathDistance = pathDistance;
        this.isUsed = isUsed;
        this.predecessor = predecessor;
    }

    public int getPathDistance() {
        return pathDistance;
    }

    public void setPathDistance(int pathDistance) {
        this.pathDistance = pathDistance;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }
}
