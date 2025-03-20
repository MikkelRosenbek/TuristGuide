package org.example.turistguideapi.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private Location lokation;
    private List<Tag> tags;


    public TouristAttraction(String name, String description, Location lokation, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.lokation = lokation;
        this.tags = tags;

    }
    public TouristAttraction(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Location getLokation() {
        return lokation;
    }

    public void setLokation(Location lokation) {
        this.lokation = lokation;
    }

    public void setTags(List<Tag> tag) {
        this.tags = tag;
    }
}

