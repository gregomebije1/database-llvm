package com.gregomebije.database;

import java.util.*;

public class Table {
    private String tableName;
    private List<String> columns;
    private List<Row> rows;

    public Table(String tableName, List<String> columns) {
        this.tableName = tableName;
        this.columns = columns;
        this.rows = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public List<Row> getRows() {
        return rows;
    }

    public void updateRow(int rowIndex, String columnName, Object value) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            rows.get(rowIndex).setColumn(columnName, value);
        }
    }

    public void deleteRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < rows.size()) {
            rows.remove(rowIndex);
        }
    }

    public List<Row> selectAll() {
        return rows;
    }

    public List<Row> selectWhere(String columnName, Object value) {
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if (row.getColumn(columnName) != null && row.getColumn(columnName).equals(value)) {
                result.add(row);
            }
        }
        return result;
    }
}
