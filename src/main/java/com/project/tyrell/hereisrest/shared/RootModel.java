package com.project.tyrell.hereisrest.shared;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.project.tyrell.hereisrest.shared.RootModelClasses.HoursModel;
import com.project.tyrell.hereisrest.shared.RootModelClasses.SubscriptionPlan;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class RootModel {
    public String id;
    public String name;
    public String description;
    public float rating;
    public String website;
    public String address;
    public String contact;
    public boolean parking;
    public boolean animalsAllowed;
    public boolean paidVisit;
    public boolean wc;
    public boolean finePlaced;
    public boolean handicapAccessibility;
    public List<HoursModel> hours = new ArrayList<>();
    public List<String> images = new ArrayList<>();
    public String location;
    public String longitude;
    public String latitude;
    public String city;
    public SubscriptionPlan subscriptionPlan = SubscriptionPlan.NON_PAID;  //subject to change
    public boolean active = true;

    protected boolean matchesFilter(RootModel rootModelEntity, RootFilterBody filter) {
        return (!filter.parking || rootModelEntity.parking) &&
                (!filter.finePlaced || rootModelEntity.finePlaced) &&
                (!filter.animalsAllowed || rootModelEntity.animalsAllowed) &&
                (!filter.handicapAccessibility || rootModelEntity.handicapAccessibility) &&
                (filter.city == null || (filter.city.equals(rootModelEntity.city)));
    }
}
