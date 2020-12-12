package com.example.marvelcomics.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Resource {
    @PrimaryKey(autoGenerate = true)
    private Integer resourceID;
    @ColumnInfo(name = "resource_uri")
    private String resourceURI;
    private String name;
    private String type;

    public Resource() {
    }

    /**
     * {
     * "resourceURI": "http://gateway.marvel.com/v1/public/characters/1009156",
     * "name": "Apocalypse",
     * "type": "character"
     * },
     */
    public Resource(String resourceURI, String name, String type) {
        this.resourceURI = resourceURI;
        this.name = name;
        this.type = type;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
