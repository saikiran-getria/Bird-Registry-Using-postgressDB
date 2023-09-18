package com.ria.birdService.model.dto;

import java.util.List;

public class BirdDTO {

    private String id;
    private String name;
    private String family;
    private List<String> continents;
    private String added;
    private Boolean visible;

    public BirdDTO(String id, String name, String family, List<String> continents, String added, Boolean visible) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.continents = continents;
        this.added = added;
        this.visible = visible;
    }

    public BirdDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
