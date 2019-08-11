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

        <delete id="remove" resultType="java.lang.Integer">
            DELETE FROM
            <include refid="base_table"/>
            WHERE 1=1
            <include refid="base_where"/>
        </delete>

        <update id="update" resultType="java.lang.Integer">
            UPDATE
            <include refid="base_table"/>
            SET
            <trim suffixOverrides=",">
                <include refid="base_update_condition"/>
            </trim>
            WHERE
            <include refid="base_where"/>
        </update>

        <insert id="insert">
            INSERT INTO
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
        <if test="${column.lowerCaseColumnName} != null"> AND ${column.columnName}=<#noparse>#{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse></if>
        </#list>
    </sql>

    <sql id="base_insert_field">
        <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null">${column.columnName},</if>
        </#list>
    </sql>

    <sql id="base_insert_condition">
        <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null"><#noparse>#{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse>,</if>
        </#list>
    </sql>

    <sql id="base_update_condition">
        <#list columns as column>
        <if test="${column.lowerCaseColumnName} != null">${column.columnName}=<#noparse>#{</#noparse>${column.lowerCaseColumnName}<#noparse>}</#noparse>,</if>
        </#list>
    </sql>

</mapper>