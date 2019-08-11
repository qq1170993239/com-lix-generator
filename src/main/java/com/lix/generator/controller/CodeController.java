package com.lix.generator.controller;

import com.alibaba.fastjson.JSONObject;
import com.lix.generator.service.CodeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/code")
    public void code(@RequestParam("sql") String sql, @RequestParam("author") String author, @RequestParam("packagePath") String packagePath, HttpServletResponse response) throws IOException {
        JSONObject data = new JSONObject(8);
        data.put("sql", sql);
        data.put("author", author);
        data.put("packagePath", packagePath);
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
