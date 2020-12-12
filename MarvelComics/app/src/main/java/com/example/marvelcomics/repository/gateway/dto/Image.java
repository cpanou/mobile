package com.example.marvelcomics.repository.gateway.dto;

import java.util.function.Function;

public class Image {
    private String path;
    private String extension;

    public Image() {
    }

    /**
     * Example
     * {
     * "path": "http://i.annihil.us/u/prod/marvel/i/mg/c/b0/4bc6494ed6eb4",
     * "extension": "jpg"
     * }
     */
    public String url() {
        return path + "." + extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
