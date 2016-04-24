package com.fieryinferno.aggregator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atahmasebi on 4/23/16.
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class Table {
    private List<Standing> table = new ArrayList<>();

    public List<Standing> getTable() {
        return table;
    }

    public void setTable(List<Standing> table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Table{" +
                "table=" + table +
                '}';
    }
}
