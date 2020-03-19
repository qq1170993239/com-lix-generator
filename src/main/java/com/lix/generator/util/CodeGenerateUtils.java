package com.lix.generator.util;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author lix
 * @Date 2019/8/8 0008
 */
public class CodeGenerateUtils {


    private static final Map<String, String> templateNames;

    static {
        templateNames = new HashMap<>();
        templateNames.put("Mapper.ftl", "Mapper.xml");
        templateNames.put("DAO.ftl", "DAO.java");
        templateNames.put("ServiceInterface.ftl", "Service.java");
        templateNames.put("ServiceImpl.ftl", "ServiceImpl.java");
        templateNames.put("Controller.ftl", "Controller.java");
        templateNames.put("Model.ftl", ".java");
    }

    public static void generatorCode(JSONObject data, ZipOutputStream zip) throws Exception {
        TableHelper tableHelper = new TableHelper(data.getString("sql"));
        String filePath = "main" + File.separator + "java" + File.separator + data.getString("packagePath").replace(".", File.separator) + File.separator;
        try {
            JSONObject dataMap = new JSONObject();
            dataMap.put("tableName", tableHelper.getTableName());
            dataMap.put("tableComment", tableHelper.getTableComment());
            dataMap.put("className", tableHelper.getClassName());
            dataMap.put("classname", tableHelper.getClassname());
            dataMap.put("columns", tableHelper.getColumns());
            dataMap.put("author", data.getString("author"));
            dataMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            dataMap.put("packageName", data.getString("packagePath"));
            for (Iterator<Map.Entry<String, String>> iterator = templateNames.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String> next = iterator.next();
                try {
                    StringWriter out = new StringWriter();
                    Template template = FreeMarkerTemplateUtils.getTemplate(next.getKey());
                    template.process(dataMap, out);
                    //添加到zip
                    zip.putNextEntry(new ZipEntry(filePath + tableHelper.getClassName() +next.getValue()));
                    IOUtils.write(out.toString(), zip, "UTF-8");
                    IOUtils.closeQuietly(out);
                    zip.closeEntry();
                } catch (IOException e) {
                    throw new RuntimeException("渲染模板失败", e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(zip);
        }
    }


}
