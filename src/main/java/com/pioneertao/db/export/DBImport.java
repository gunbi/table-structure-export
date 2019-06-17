package com.pioneertao.db.export;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBImport {
    //数据库连接信息
    private static final String jdbc_url="jdbc:mysql://192.168.0.23:3306/xxl-job";
    private static final String jdbc_user="root";
    private static final String jdbc_pwd="d1212123d";
    /**
     * 查询表结构信息
     */
    public static List<Table> getAllColumn() throws ClassNotFoundException {
        List<Table> tableList=new ArrayList<Table>();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pwd);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableRet = metaData.getTables(null, "%","%",new String[]{"TABLE"});
            while(tableRet.next()){
                Table table=new Table();
                String tableName=tableRet.getString("TABLE_NAME");
                table.setTableName(tableName);
                ResultSet colRet = metaData.getColumns(null,"%", tableName,"%");
                List<Map<String,String>> columnList=new ArrayList<>();
                while(colRet.next()) {
                    Map<String, String> map = new HashMap<>();
                    /*TableColumn tableColumn=new TableColumn();
                    tableColumn.setColumnDescribe(colRet.getString("REMARKS"));
                    tableColumn.setColumnName(colRet.getString("COLUMN_NAME"));
                    tableColumn.setColumnType(colRet.getString("TYPE_NAME"));
                    columnList.add(tableColumn);*/

                    String remarks = colRet.getString("REMARKS");
                    if(remarks.length()==0||remarks==null){
                        remarks="空";
                    }
                    map.put("COLUMN_NAME",colRet.getString("COLUMN_NAME"));
                    map.put("TYPE_NAME",colRet.getString("TYPE_NAME"));
                    int nullable = colRet.getInt("NULLABLE");
                    map.put("NULLABLE",nullable==1?"是":"否");
                    map.put("REMARKS",remarks);
                    columnList.add(map);
                }
                table.setTableColumn(columnList);
                tableList.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("已从数据库获得表结构");
        System.out.println(tableList);
        return tableList;
    }
}
