package com.project.tyrell.hereisrest.shared;

import com.project.tyrell.hereisrest.root.RootModel;

public class ModelDistanceBean
{
    public RootModel model;

    public double distance;

    public ModelDistanceBean(RootModel model, double distance) {
        this.model = model;
        this.distance = distance;
    }
}