<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">


<mapper namespace="${packageName}.mapper.${className}DAO">

    <!--create by ${author} at ${date}-->
    <select id="select" resultType="${packageName}.entity.${className}">
        SELECT
        <trim suffixOverrides=",">
            <include refid="base_field"/>
        </trim>
        FROM
        <include refid="base_table"/>
        WHERE 1=1
        <include refid="base_where"/>
    </select>

    <delete id="remove" parameterType="${packageName}.entity.${className}">
        DELETE FROM
        <include refid="base_table"/>
        WHERE 1=1
        <include refid="base_where"/>
    </delete>

    <update id="updateByPk" parameterType="${packageName}.entity.${className}">
        UPDATE
        <include refid="base_table"/>
        SET
        <trim suffixOverrides=",">
            <include refid="base_update_condition"/>
        </trim>
        WHERE 1=1
    <#list columns as column>
        <#if column.key>
            AND ${column.columnName}=<#noparse>#{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse>
        </#if>
    </#list>
    </update>

    <insert id="insert" parameterType="${packageName}.entity.${className}">
        INSERT INTO <include refid="base_table"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="base_insert_field"/>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="base_insert_condition"/>
        </trim>
    </insert>

    <sql id="base_table">
    ${tableName}
    </sql>
    <sql id="base_field">
    <#list columns as column>
    ${column.columnName}        as      ${column.lowerCaseColumnName},
    </#list>
    </sql>

    <sql id="base_where">
    <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null"> AND ${column.columnName}=<#noparse>
            #{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse></if>
    </#list>
    </sql>

    <sql id="base_insert_field">
    <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null">${column.columnName},</if>
    </#list>
    </sql>

    <sql id="base_insert_condition">
    <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null"><#noparse>#{</#noparse>${column.lowerCaseColumnName}<#noparse>
            }</#noparse>,
        </if>
    </#list>
    </sql>

    <sql id="base_update_condition">
    <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null">${column.columnName}=<#noparse>
            #{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse>,
        </if>
    </#list>
    </sql>

</mapper>