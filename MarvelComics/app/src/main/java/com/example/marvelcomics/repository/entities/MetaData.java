package com.example.marvelcomics.repository.entities;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.marvelcomics.repository.gateway.dto.CharactersDataContainer;
import com.example.marvelcomics.repository.gateway.dto.ComicsDataContainer;
import com.example.marvelcomics.repository.gateway.dto.CreatorsDataContainer;

@Entity(tableName = "meta_data_table")
public class MetaData {
    @PrimaryKey
    private Integer id;
    private String table;
    private Integer offset;
    private Integer total;

    @Ignore
    public static final MetaData CHARACTERS_INITIAL = new MetaData(1, 0, 1, Character.META_TAG);
    @Ignore
    public static final MetaData COMICS_INITIAL = new MetaData(2, 0, 1, Comic.META_TAG);
    @Ignore
    public static final MetaData CREATORS_INITIAL = new MetaData(3, 0, 1, Creator.META_TAG);

    public MetaData() {
    }

    @Ignore
    public MetaData(Integer id, Integer offset, Integer total, String table) {
        this.id = id;
        this.offset = offset;
        this.total = total;
        this.table = table;
    }

    @Ignore
    public static MetaData from(CharactersDataContainer collection) {
        MetaData metaData = CHARACTERS_INITIAL;
        metaData.setOffset(collection.getOffset() + collection.getCount());
        metaData.setTotal(collection.getTotal());
        metaData.setTable(Character.META_TAG);
        return metaData;
    }

    @Ignore
    public static MetaData from(ComicsDataContainer collection) {
        MetaData metaData = COMICS_INITIAL;
        metaData.setOffset(collection.getOffset() + collection.getCount());
        metaData.setTotal(collection.getTotal());
        metaData.setTable(Comic.META_TAG);
        return metaData;
    }

    @Ignore
    public MetaData from(CreatorsDataContainer collection) {
        MetaData metaData = CREATORS_INITIAL;
        metaData.setOffset(collection.getOffset());
        metaData.setTotal(collection.getTotal());
        metaData.setTable(Creator.META_TAG);
        return metaData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
