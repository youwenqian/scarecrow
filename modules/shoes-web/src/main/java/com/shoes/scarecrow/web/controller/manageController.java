package com.shoes.scarecrow.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-4
 * Time 上午2:03
 */
@Controller
public class manageController {
    Logger log = Logger.getLogger(manageController.class);
    @RequestMapping("/manage/{path}")
    public String dispathchPath(@PathVariable("path") String path){
        log.info("跳转到"+path);
        return path;
    }
}
