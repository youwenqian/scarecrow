package com.shoes.scarecrow.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-4
 * Time 上午1:54
 */
@Controller
public class MerchantController {
    Logger log = Logger.getLogger(MerchantController.class);
    @RequestMapping("/merchant/{path}")
    public String dispathchPath(@PathVariable("path") String path){
        log.info("跳转到"+path);
        return path;
    }
}
