package com.example.marvel.repository.gateway.dto;

import com.example.marvel.repository.entities.Character;

import java.util.List;

public class CharactersDataContainer {
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<Character> results;

    public CharactersDataContainer() {
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
