package com.lix.generator.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lix
 * @Date 2019/8/8 0008
 */
@RestController
public class Contrller {


    @Autowired
    private IService service;


}
