package com.pioneertao.db.export;

import java.util.List;
import java.util.Map;

public class Table {
    private String tableName;
    private List<Map<String,String>> tableColumn;


    public Table() {
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", tableColumn=" + tableColumn +
                '}';
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Map<String, String>> getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(List<Map<String, String>> tableColumn) {
        this.tableColumn = tableColumn;
    }

    public Table(String tableName, List<Map<String, String>> tableColumn) {

        this.tableName = tableName;
        this.tableColumn = tableColumn;
    }
}

