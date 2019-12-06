package com.intranet.onlineshop.domain.models.service;

import java.util.Objects;

public class NodeDepthModel {

    private String name;
    private int depth;

    public NodeDepthModel(String name, int depth) {
        this.name = name;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public int getDepth() {
        return depth;
    }


}
