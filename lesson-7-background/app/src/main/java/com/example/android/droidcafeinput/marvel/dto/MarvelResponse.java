package com.example.android.droidcafeinput.marvel.dto;


public class MarvelResponse {
    private String code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private ComicsData data;

    public MarvelResponse() {
    }

    public MarvelResponse(String code, String status, String copyright, String attributionText, String attributionHTML, ComicsData data) {
        this.code = code;
        this.status = status;
        this.copyright = copyright;
        this.attributionText = attributionText;
        this.attributionHTML = attributionHTML;
        this.data = data;
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

    public ComicsData getData() {
        return data;
    }

    public void setData(ComicsData data) {
        this.data = data;
    }
}
