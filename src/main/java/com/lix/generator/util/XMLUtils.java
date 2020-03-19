package com.lix.generator.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.List;

public class XMLUtils {

    public static JSONObject parse(String xml) throws DocumentException {
        if (StringUtils.isBlank(xml)) return null;
        SAXReader reader = new SAXReader();
        Document read = reader.read(new StringReader(xml));
        Element rootElement = read.getRootElement();
        JSONObject container = new JSONObject();
        convert(rootElement, container);
        return container.getJSONObject(rootElement.getName());
    }


    private static void convert(Element ele, JSONObject jsonObject) {
        List<Element> elements = ele.elements();
        for (Element sub : elements) {
            if (sub.isTextOnly()) {
                jsonObject.put(sub.getName(), sub.getTextTrim());
                continue;
            }
            JSONObject obj = new JSONObject();
            convert(sub, obj);

            Object value = jsonObject.get(sub.getName());
            if (value == null) {
                jsonObject.put(sub.getName(), obj);
                continue;
            }
            if (value instanceof JSONObject) {
                JSONArray array = new JSONArray();
                array.add(value);
                array.add(obj);
                jsonObject.put(sub.getName(), array);
            } else {
                ((JSONArray)value).add(obj);
            }
        }
    }


}
