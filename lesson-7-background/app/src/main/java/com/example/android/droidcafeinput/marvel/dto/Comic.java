package com.example.android.droidcafeinput.marvel.dto;

import java.util.List;

public class Comic {
    private Integer id;
    private Integer digitalId;
    private Integer issueNumber;
    private Integer pageCount;
    private String title;
    private String variantDescription;
    private String description;
    private String format;
    private String issn;
    private String modified; //"2020-02-07T09:35:32-0500"
    private List<Image> images;
    private Image thumbnail;
    private ResourceCollection characters;

    public Comic() {
    }

    /**
     * "thumbnail": {
     * "path": "http://i.annihil.us/u/prod/marvel/i/mg/c/b0/4bc6494ed6eb4",
     * "extension": "jpg"
     * },
     * "images": [
     * {
     * "path": "http://i.annihil.us/u/prod/marvel/i/mg/c/b0/4bc6494ed6eb4",
     * "extension": "jpg"
     * }
     * ],
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ResourceCollection getCharacters() {
        return characters;
    }

    public void setCharacters(ResourceCollection characters) {
        this.characters = characters;
    }
}
