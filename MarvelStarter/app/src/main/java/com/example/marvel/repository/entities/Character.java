package com.example.marvel.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marvel.repository.gateway.dto.Image;
import com.example.marvel.repository.gateway.dto.ResourceCollection;

@Entity(tableName = "characters_table")
public class Character {

    @PrimaryKey
    private Integer id;

    private String name;
    private String description;
    private String modified;

    @ColumnInfo(name = "resource_uri")
    private String resourceURI;

    @Ignore
    private Image thumbnail;
    private String thumbnail_url;

    @Ignore
    private ResourceCollection comics;
    @Ignore
    private ResourceCollection stories;
    @Ignore
    private ResourceCollection events;
    @Ignore
    private ResourceCollection series;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ResourceCollection getComics() {
        return comics;
    }

    public void setComics(ResourceCollection comics) {
        this.comics = comics;
    }

    public ResourceCollection getStories() {
        return stories;
    }

    public void setStories(ResourceCollection stories) {
        this.stories = stories;
    }

    public ResourceCollection getEvents() {
        return events;
    }

    public void setEvents(ResourceCollection events) {
        this.events = events;
    }

    public ResourceCollection getSeries() {
        return series;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public void setSeries(ResourceCollection series) {
        this.series = series;
    }
}
