package com.example.marvelcomics.repository.entities;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marvelcomics.repository.gateway.dto.Image;
import com.example.marvelcomics.repository.gateway.dto.ResourceCollection;

@Entity(tableName = "character_table")
public class Character {
    public static String META_TAG = "character_table";
    @PrimaryKey
    private Integer id;
    private String name;
    private String description;
    private String modified;
    @ColumnInfo(name = "resource_uri")
    private String resourceURI;
    @Ignore
    private Image thumbnail;
    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;


    @Ignore
    private ResourceCollection comics;
    private Integer comics_available;
    @Ignore
    private ResourceCollection series;
    private Integer series_available;
    @Ignore
    private ResourceCollection stories;
    private Integer stories_available;

    public ResourceCollection getComics() {
        return comics;
    }

    public void setComics(ResourceCollection comics) {
        this.comics = comics;
    }

    public Integer getComics_available() {
        return comics_available;
    }

    public void setComics_available(Integer comics_available) {
        this.comics_available = comics_available;
    }

    public ResourceCollection getSeries() {
        return series;
    }

    public void setSeries(ResourceCollection series) {
        this.series = series;
    }

    public Integer getSeries_available() {
        return series_available;
    }

    public void setSeries_available(Integer series_available) {
        this.series_available = series_available;
    }

    public ResourceCollection getStories() {
        return stories;
    }

    public void setStories(ResourceCollection stories) {
        this.stories = stories;
    }

    public Integer getStories_available() {
        return stories_available;
    }

    public void setStories_available(Integer stories_available) {
        this.stories_available = stories_available;
    }

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }
}
