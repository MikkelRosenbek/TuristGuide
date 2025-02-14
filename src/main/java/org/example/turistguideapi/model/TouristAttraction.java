package org.example.turistguideapi.model;

import java.util.UUID;

public class TouristAttraction {
    private String name;
    private String description;
    private UUID attractionId;

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
        this.attractionId = attractionId;
    }

    public TouristAttraction() {
        this.attractionId = UUID.randomUUID();
    }

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

    public void setAttractionId(UUID attractionId) {
        this.attractionId = attractionId;
    }

    public UUID getAttractionId() {
        return attractionId;
    }
}

