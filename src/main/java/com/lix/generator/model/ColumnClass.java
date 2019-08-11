package com.lix.generator.model;

import java.util.List;

/**
 * @author lix
 * @Date 2019/8/10 0010
 */
public class ColumnClass {

    /**是否是主键*/
    private boolean key;
    /** 数据库字段名称 **/
    private String columnName;
    /** 数据库字段类型 **/
    private String columnType;
    /** java数据类型 **/
    private String attrType;
    /** 数据库字段首字母大写且去掉下划线字符串 **/
    private String upperCaseColumnName;
    /** 数据库字段首字母小写且去掉下划线字符串 **/
    private String lowerCaseColumnName;
    /** 数据库字段注释 **/
    private String columnComment;

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
    public void setColumnComment(List<String> columnComment) {
        StringBuilder builder = new StringBuilder();
        columnComment.forEach(e -> builder.append(e));
        this.columnComment = builder.toString()
                .toUpperCase()
                .replace("DEFAULT", "")
                .replace("NULL", "")
                .replace("COMMENT", "")
                .replace("NOT", "");
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName.toUpperCase();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getUpperCaseColumnName() {
        return upperCaseColumnName;
    }

    public void setUpperCaseColumnName(String upperCaseColumnName) {
        this.upperCaseColumnName = upperCaseColumnName;
    }

    public String getLowerCaseColumnName() {
        return lowerCaseColumnName;
    }

    public void setLowerCaseColumnName(String lowerCaseColumnName) {
        this.lowerCaseColumnName = lowerCaseColumnName;
    }
}
