package org.example.turistguideapi.model;


public enum Location {
    KØBENHAVN("København"),
    HERNING("Herning");

    private final String displayName;

    Location(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
