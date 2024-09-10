package com.gregomebije.database;

import java.util.*;
import java.util.regex.*;

public class Database {
    private Map<String, Table> tables;

    public Database() {
        this.tables = new HashMap<>();
    }

    public void createTable(String tableName, List<String> columns) {
        tables.put(tableName, new Table(tableName, columns));
    }

    public void dropTable(String tableName) {
        tables.remove(tableName);
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public Set<String> listTables() {
        return tables.keySet();
    }

    public String runSQL(String sql) {
        sql = sql.trim().toUpperCase();

        if (sql.startsWith("CREATE TABLE")) {
            return createTableSQL(sql);
        } else if (sql.startsWith("INSERT INTO")) {
            return insertRowSQL(sql);
        } else if (sql.startsWith("SELECT")) {
            return selectSQL(sql);
        } else if (sql.startsWith("UPDATE")) {
            return updateSQL(sql);
        } else if (sql.startsWith("DELETE FROM")) {
            return deleteSQL(sql);
        }
        return "Invalid SQL Command";
    }

    private String createTableSQL(String sql) {
        Pattern pattern = Pattern.compile("CREATE TABLE (\\w+) \\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            List<String> columns = Arrays.asList(matcher.group(2).split(","));
            createTable(tableName, columns);
            return "Table '" + tableName + "' created.";
        }
        return "Error in CREATE TABLE syntax.";
    }

    private String insertRowSQL(String sql) {
        Pattern pattern = Pattern.compile("INSERT INTO (\\w+) \\(([^)]+)\\) VALUES \\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String[] columnNames = matcher.group(2).split(",");
            String[] values = matcher.group(3).split(",");

            Table table = getTable(tableName);
            if (table == null) return "Table '" + tableName + "' not found.";

            Row row = new Row();
            for (int i = 0; i < columnNames.length; i++) {
                row.setColumn(columnNames[i].trim(), values[i].trim());
            }
            table.addRow(row);
            return "Row inserted into table '" + tableName + "'.";
        }
        return "Error in INSERT INTO syntax.";
    }

    private String selectSQL(String sql) {
        Pattern pattern = Pattern.compile("SELECT \\* FROM (\\w+)(?: WHERE (\\w+) = '(\\w+)')?");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String whereColumn = matcher.group(2);
            String whereValue = matcher.group(3);

            Table table = getTable(tableName);
            if (table == null) return "Table '" + tableName + "' not found.";

            List<Row> result;
            if (whereColumn != null && whereValue != null) {
                result = table.selectWhere(whereColumn, whereValue);
            } else {
                result = table.selectAll();
            }

            StringBuilder sb = new StringBuilder();
            for (Row row : result) {
                sb.append(row.toString()).append("\n");
            }
            return sb.toString().trim(); //Ensure there's no trailing newline
        }
        return "Error in SELECT syntax.";
    }

    private String updateSQL(String sql) {
        Pattern pattern = Pattern.compile("UPDATE (\\w+) SET (\\w+) = '(\\w+)' WHERE (\\w+) = '(\\w+)'");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String updateColumn = matcher.group(2);
            String updateValue = matcher.group(3);
            String whereColumn = matcher.group(4);
            String whereValue = matcher.group(5);

            Table table = getTable(tableName);
            if (table == null) return "Table '" + tableName + "' not found.";

            List<Row> rows = table.selectWhere(whereColumn, whereValue);
            for (Row row : rows) {
                row.setColumn(updateColumn, updateValue);
            }
            return "Rows updated in table '" + tableName + "'.";
        }
        return "Error in UPDATE syntax.";
    }

    private String deleteSQL(String sql) {
        Pattern pattern = Pattern.compile("DELETE FROM (\\w+) WHERE (\\w+) = '(\\w+)'");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group(1);
            String whereColumn = matcher.group(2);
            String whereValue = matcher.group(3);

            Table table = getTable(tableName);
            if (table == null) return "Table '" + tableName + "' not found.";

            List<Row> rows = table.selectWhere(whereColumn, whereValue);
            for (Row row : rows) {
                table.deleteRow(table.getRows().indexOf(row));
            }
            return "Rows deleted from table '" + tableName + "'.";
        }
        return "Error in DELETE syntax.";
    }
}
