package com.lix.generator.util;

import com.lix.generator.model.ColumnClass;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lix
 * @Date 2019/8/10 0010
 */
public class TableHelper {

    private String tableName;
    private String tableComment;
    private String className; // TestUser
    private String classname; // testUser
    private List<ColumnClass> columns = new ArrayList<>();

    public TableHelper(String sql) throws JSQLParserException {
        //配置信息
        Configuration config = getConfig();
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement parse = parserManager.parse(new StringReader(sql));
        MyStatementVisitor myStatementVisitor = new MyStatementVisitor();
        parse.accept(myStatementVisitor);
        CreateTable createTable = myStatementVisitor.getCreateTable();
        // 获取主键
        Optional<Index> primary_key = createTable.getIndexes().stream()
                .filter(e -> e.getType() != null && e.getType().indexOf("KEY") != -1)
                .findAny();
        List<String> columnsNames = null;
        if(primary_key != null && primary_key.isPresent()){
            columnsNames = primary_key.get().getColumnsNames();
        }
        this.tableName = createTable.getTable().getName().toUpperCase();
        this.className = tableToJava(tableName, null);
        this.classname = StringUtils.capitalize(className);
        this.setTableComment(createTable.getTableOptionsStrings());
        ColumnClass columnClass = null;
        for (ColumnDefinition columnDefinition : createTable.getColumnDefinitions()){
            columnClass = new ColumnClass();
            columnClass.setColumnName(columnDefinition.getColumnName());
            columnClass.setColumnType(columnDefinition.getColDataType().getDataType());
            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnClass.getColumnType(), "unknowType" );
            columnClass.setAttrType(attrType);
            columnClass.setColumnComment(columnDefinition.getColumnSpecStrings());
            columnClass.setUpperCaseColumnName(columnToJava(columnDefinition.getColumnName()));
            columnClass.setLowerCaseColumnName(StringUtils.uncapitalize(columnClass.getUpperCaseColumnName()));
            if(columnsNames != null && columnsNames.contains(columnDefinition.getColumnName())){
                columnClass.setKey(true);
            }
            columns.add(columnClass);
        }
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "" );
        }
        return columnToJava(tableName);
    }


    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public void setTableComment(List<?> tableComment) {
        StringBuilder builder = new StringBuilder();
        tableComment.forEach(e -> builder.append(e.toString()));
        this.tableComment = builder.toString().toUpperCase()
                .replace("ENGINE", "")
                .replace("INNODB", "")
                .replace("=", "")
                .replace("COMMENT", "")
                .replace("UTF8", "")
                .replace("DEFAULT", "")
                .replace("CHARSET", "");
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnClass> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnClass> columns) {
        this.columns = columns;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
