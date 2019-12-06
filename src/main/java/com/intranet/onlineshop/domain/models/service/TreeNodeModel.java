package com.intranet.onlineshop.domain.models.service;

import java.util.List;

public class TreeNodeModel {

    private String name;
    private List<TreeNodeModel> successors;

    public TreeNodeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNodeModel> getSuccessors() {
        return successors;
    }

    public void setSuccessors(List<TreeNodeModel> successors) {
        this.successors = successors;
    }
}
