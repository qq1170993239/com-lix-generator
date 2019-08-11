package com.lix.generator.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lixiang on 2019/8/9 0009.
 */
public class DefaultView extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        //主页
        registry.addViewController("/").setViewName("forward:/index");
    }

}
