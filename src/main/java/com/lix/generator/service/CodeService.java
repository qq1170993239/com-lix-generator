package com.lix.generator.service;

import com.alibaba.fastjson.JSONObject;

public interface CodeService {

    byte[] generatorCode(JSONObject data) throws Exception;

}
