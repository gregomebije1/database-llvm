package com.gregomebije.database;

import java.util.*;

public class Row {
    private Map<String, Object> data;

    public Row() {
        this.data = new HashMap<>();
    }

    public void setColumn(String columnName, Object value) {
        data.put(columnName, value);
    }

    public Object getColumn(String columnName) {
        return data.get(columnName);
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
