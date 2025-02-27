package org.example.turistguideapi.model;

public enum Tag {
    BØRNEVENLIG("Børnevenlig"),
    GRATIS("Gratis"),
    KUNST("Kunst"),
    NATUR("Natur");


    private String displayName;

    Tag(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

