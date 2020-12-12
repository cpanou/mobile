package com.example.marvelcomics.repository.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marvelcomics.repository.gateway.dto.Image;
import com.example.marvelcomics.repository.gateway.dto.Price;
import com.example.marvelcomics.repository.gateway.dto.ResourceCollection;

import java.util.List;

@Entity(tableName = "comic_table")
public class Comic {
    public static String META_TAG = "comic_table";
    @PrimaryKey
    private Integer id;
    @ColumnInfo(name = "digital_id")
    private Integer digitalId;
    @ColumnInfo(name = "issue_number")
    private Integer issueNumber;
    @ColumnInfo(name = "page_count")
    private Integer pageCount;
    private String title;
    @ColumnInfo(name = "variant_description")
    private String variantDescription;
    private String description;
    private String format;
    private String issn;
    private String modified; //"2020-02-07T09:35:32-0500"
    //GENERATED AT FETCH
    @Ignore
    private Image thumbnail;
    @ColumnInfo(name = "thumbnail_url")
    private String thumbnailUrl;

    //GENERATED AT FETCH
    @Ignore
    private ResourceCollection characters;
    @ColumnInfo(name = "characters")
    private String charactersUrl;
    @Ignore
    private ResourceCollection creators;
    @ColumnInfo(name = "creators")
    private String creatorsUrl;
    @Ignore
    private ResourceCollection stories;
    @ColumnInfo(name = "stories")
    private String storiesUrl;
    @Ignore
    private ResourceCollection series;
    @ColumnInfo(name = "series")
    private String seriesUrl;
    @Ignore
    private List<Resource> variants;
    @ColumnInfo(name = "variants_count")
    private Integer variantsCounts;
    @Ignore
    private List<Price> prices;
    @ColumnInfo(name = "price")
    private Integer price;

    @Ignore
    private List<Image> images;

    public Comic() {
    }

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCharactersUrl() {
        return charactersUrl;
    }

    public void setCharactersUrl(String charactersUrl) {
        this.charactersUrl = charactersUrl;
    }

    public ResourceCollection getCreators() {
        return creators;
    }

    public void setCreators(ResourceCollection creators) {
        this.creators = creators;
    }

    public String getCreatorsUrl() {
        return creatorsUrl;
    }

    public void setCreatorsUrl(String creatorsUrl) {
        this.creatorsUrl = creatorsUrl;
    }

    public ResourceCollection getStories() {
        return stories;
    }

    public void setStories(ResourceCollection stories) {
        this.stories = stories;
    }

    public String getStoriesUrl() {
        return storiesUrl;
    }

    public void setStoriesUrl(String storiesUrl) {
        this.storiesUrl = storiesUrl;
    }

    public ResourceCollection getSeries() {
        return series;
    }

    public void setSeries(ResourceCollection series) {
        this.series = series;
    }

    public String getSeriesUrl() {
        return seriesUrl;
    }

    public void setSeriesUrl(String seriesUrl) {
        this.seriesUrl = seriesUrl;
    }

    public List<Resource> getVariants() {
        return variants;
    }

    public void setVariants(List<Resource> variants) {
        this.variants = variants;
    }

    public Integer getVariantsCounts() {
        return variantsCounts;
    }

    public void setVariantsCounts(Integer variantsCounts) {
        this.variantsCounts = variantsCounts;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
