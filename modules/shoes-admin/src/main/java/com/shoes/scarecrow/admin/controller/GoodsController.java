package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.common.utils.DateUtils;
import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.GoodsCondition;
import com.shoes.scarecrow.persistence.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/9 20:31
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/brandList")
    public String brandList(HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> param = WebUtils.postData(request);
//        WebUtils.setPage(param,request);
        try{
            GoodsCondition condition = new GoodsCondition();
            List<Goods> goods = goodsService.queryByCondition(condition);
            int count = goodsService.queryCountByCondition(condition);
            request.setAttribute("brands", goods);
            request.setAttribute("count", count);
            request.setAttribute("condition", condition);
            return "goods/goodsList";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }










}