package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.WebUtils;
import com.shoes.scarecrow.persistence.domain.Color;
import com.shoes.scarecrow.persistence.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 22:47
 */
@Controller
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @RequestMapping("/colorList")
    public String colorList( HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> param = WebUtils.postData(request);
        WebUtils.setPage(param,request);
        try{
            List<Color> colors = colorService.queryByCondition(param);
            int count = colorService.queryCountByCondition(param);
            request.setAttribute("colors", colors);
            request.setAttribute("count", count);
            WebUtils.setDataParam(param, request);
            return "color/colorList";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/toColorAdd")
    public String toColorAdd(HttpServletRequest request, HttpServletResponse response){
        return "color/colorAdd";
    }

    @RequestMapping("/colorAdd")
    @ResponseBody
    public String colorAdd(Color color, HttpServletRequest request, HttpServletResponse response){
        int count =0;
        try{
            count = colorService.save(color);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/toColorEdit")
    public String toColorEdit(Integer id, HttpServletRequest request, HttpServletResponse response){
        Color color = colorService.queryById(id);
        request.setAttribute("color", color);
        return "color/colorEdit";
    }

    @RequestMapping("/colorEdit")
    @ResponseBody
    public String colorEdit(Color color, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            count = colorService.update(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/delColor")
    @ResponseBody
    public String delColor(Integer id, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            List<Integer> ids = new ArrayList<>();
            ids.add(id);
            count = colorService.delById(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }
    @RequestMapping("/batchDel")
    @ResponseBody
    public String batchDel(Integer[] ids, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            count = colorService.delById(Arrays.asList(ids));
            if (count == ids.length) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}