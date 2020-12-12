package com.example.marvelcomics.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marvelcomics.repository.gateway.dto.Image;
import com.example.marvelcomics.repository.gateway.dto.ResourceCollection;

@Entity(tableName = "creator_table")
public class Creator {
    public static String META_TAG = "creator_table";
    @PrimaryKey
    private Integer id;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "middle_name")
    private String middleName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    private String suffix;
    @ColumnInfo(name = "full_name")
    private String fullName;
    private String modified;
    @ColumnInfo(name = "resource_uri")
    private String resourceURI;
    @ColumnInfo(name = "comics")
    private String comicsUri;
    //GENERATED AT FETCH
    @Ignore
    private Image thumbnail;
    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;
    //GENERATED AT FETCH
    @Ignore
    private ResourceCollection comics;
    @Ignore
    private ResourceCollection series;


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

    public ResourceCollection getComics() {
        return comics;
    }

    public void setComics(ResourceCollection comics) {
        this.comics = comics;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getComicsUri() {
        return comicsUri;
    }

    public void setComicsUri(String comicsUri) {
        this.comicsUri = comicsUri;
    }
}
