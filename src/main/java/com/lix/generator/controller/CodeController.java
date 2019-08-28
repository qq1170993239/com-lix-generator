package com.lix.generator.controller;

import com.alibaba.fastjson.JSONObject;
import com.lix.generator.service.CodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lix
 * @Date 2019/8/10 0010
 */
@Controller
@RequestMapping
public class CodeController {


    @Autowired
    private CodeService service;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    /**
     * 生成代码
     */
    @PostMapping("/code")
    public void code(String author, String packagePath, String sql, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject();
        data.put("author", author);
        data.put("packagePath", packagePath);
        data.put("sql", sql);
        System.out.println("接收到的参数：" + data);
        byte[] source = new byte[0];
        try {
            source = service.generatorCode(data);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generatorCode.zip\"");
        response.addHeader("Content-Length", "" + source.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(source, response.getOutputStream());
    }


}
