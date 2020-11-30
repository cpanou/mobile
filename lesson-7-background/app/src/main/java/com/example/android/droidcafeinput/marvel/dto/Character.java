package com.example.android.droidcafeinput.marvel.dto;

public class Character {
    private Integer id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private Image thumbnail;
    private ResourceCollection comics;
    private ResourceCollection stories;
    private ResourceCollection events;
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

    public void setSeries(ResourceCollection series) {
        this.series = series;
    }
}
