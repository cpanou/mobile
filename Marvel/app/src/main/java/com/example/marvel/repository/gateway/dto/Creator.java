package com.example.marvel.repository.gateway.dto;

public class Creator {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String fullName;
    private String modified;
    private String resourceURI;
    private Image thumbnail;
    private ResourceCollection series;
    private ResourceCollection stories;
    private ResourceCollection comics;
    private ResourceCollection events;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public ResourceCollection getSeries() {
        return series;
    }

    public void setSeries(ResourceCollection series) {
        this.series = series;
    }

    public ResourceCollection getStories() {
        return stories;
    }

    public void setStories(ResourceCollection stories) {
        this.stories = stories;
    }

    public ResourceCollection getComics() {
        return comics;
    }

    public void setComics(ResourceCollection comics) {
        this.comics = comics;
    }

    public ResourceCollection getEvents() {
        return events;
    }

    public void setEvents(ResourceCollection events) {
        this.events = events;
    }
}
