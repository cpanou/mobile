package com.example.marvel.repository.gateway.dto;

import java.util.List;

public class ComicsDataContainer {
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<Comic> results;

    public ComicsDataContainer() {
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

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }
}
