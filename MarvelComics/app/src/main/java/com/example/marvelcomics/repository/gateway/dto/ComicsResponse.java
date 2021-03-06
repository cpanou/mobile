package com.example.marvelcomics.repository.gateway.dto;


public class ComicsResponse {
    private String code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private ComicsDataContainer data;

    public ComicsResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public ComicsDataContainer getData() {
        return data;
    }

    public void setData(ComicsDataContainer data) {
        this.data = data;
    }
}
