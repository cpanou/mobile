package com.example.android.droidcafeinput.marvel.dto;

import java.util.List;

public class MarvelCollection {
    private Integer available;
    private Integer returned;
    private String collectionURI;
    private List<Item> items;

    public MarvelCollection() {
    }

    /**
     "available": 14,
     "returned": 14,
     "collectionURI": "http://gateway.marvel.com/v1/public/comics/1749/characters",
     "items": [ {}, {}  ]
     }
    */
    public MarvelCollection(Integer available, Integer returned, String collectionURI, List<Item> items) {
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
