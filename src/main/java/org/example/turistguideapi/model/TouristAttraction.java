package org.example.turistguideapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TouristAttraction {
    private String name;
    private String description;
    private String lokation;
    private List<Tag> tag;


    public TouristAttraction(String name, String description, String lokation,List<String> tag) {
        this.name = name;
        this.description = description;
        this.lokation = lokation;
        this.tag = new ArrayList<>();
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

    public List<Tag> getTag() {
        return tag;
    }

    public void setLokation(String lokation) {
        this.lokation = lokation;
    }

    public String getLokation() {
        return lokation;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}

