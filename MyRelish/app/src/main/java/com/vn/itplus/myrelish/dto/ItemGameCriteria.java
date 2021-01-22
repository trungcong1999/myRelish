package com.vn.itplus.myrelish.dto;

public class ItemGameCriteria {
    private int id;
    private String name;
    private String altName;
    private String description;

    public ItemGameCriteria(int id, String name, String altName, String description) {
        this.id = id;
        this.name = name;
        this.altName = altName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
