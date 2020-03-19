package ${packageName}.entity;

/**
* ${tableName}实体
* @author ${author}
* @Date  ${date}
*/
public class ${className} {

<#list columns as column>
// ${column.columnComment}
private ${column.attrType} ${column.lowerCaseColumnName};
</#list>


<#list columns as column>
/**
 * 获取：${column.columnComment}
 */
public ${column.attrType} get${column.upperCaseColumnName}() {
    return ${column.lowerCaseColumnName};
}

/**
 * 设置：${column.columnComment}
 */
public void set${column.upperCaseColumnName}(${column.attrType} ${column.lowerCaseColumnName}) {
    this.${column.lowerCaseColumnName} = ${column.lowerCaseColumnName};
}
</#list>
}
