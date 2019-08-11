package com.lix.generator.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lix.generator.service.CodeService;
import com.lix.generator.util.CodeGenerateUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;


/**
 * @author lix
 * @Date 2019/8/10 0010
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Override
    public byte[] generatorCode(JSONObject data) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //生成代码
        CodeGenerateUtils.generatorCode(data, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
