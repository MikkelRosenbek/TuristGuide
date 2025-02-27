package org.example.turistguideapi.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String lokation;
    private List<Tag> tags;


    public TouristAttraction(String name, String description, String lokation,List<String> tags) {
        this.name = name;
        this.description = description;
        this.lokation = lokation;
        this.tags = new ArrayList<>();

        for (String tag : tags){
            this.tags.add(Tag.valueOf(tag.toUpperCase()));
        }
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

    public void setLokation(String lokation) {
        this.lokation = lokation;
    }

    public String getLokation() {
        return lokation;
    }

    public void setTags(List<Tag> tag) {
        this.tags = tag;
    }
}

