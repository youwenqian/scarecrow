package com.shoes.scarecrow.web.service.impl;

import com.shoes.scarecrow.web.dao.IndexDao;
import com.shoes.scarecrow.web.service.IndexService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author youwenqian
 * @description
 * @time 2018/3/8 14:39
 */
@Service
public class IndexServiceImpl implements IndexService {
    private static Logger log = Logger.getLogger(IndexService.class);
    @Resource
    private IndexDao indexDao;

    public boolean login(String userName, String password) {
        return false;
    }

    public boolean register() {
        return false;
    }
}
