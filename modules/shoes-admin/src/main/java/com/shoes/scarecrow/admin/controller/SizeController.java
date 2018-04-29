package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.WebUtils;
import com.shoes.scarecrow.persistence.domain.Size;
import com.shoes.scarecrow.persistence.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 22:47
 */
@Controller
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @RequestMapping("/sizeList")
    public String sizeList( HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> param = WebUtils.postData(request);
        WebUtils.setPage(param,request);
        try{
            List<Size> sizes = sizeService.queryByCondition(param);
            int count = sizeService.queryCountByCondition(param);
            request.setAttribute("sizes", sizes);
            request.setAttribute("count", count);
            WebUtils.setDataParam(param, request);
            return "size/sizeList";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/toSizeAdd")
    public String toSizeAdd(HttpServletRequest request, HttpServletResponse response){
        return "size/sizeAdd";
    }

    @RequestMapping("/sizeAdd")
    @ResponseBody
    public String sizeAdd(Size size, HttpServletRequest request, HttpServletResponse response){
        int count =0;
        try{
            count = sizeService.save(size);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/toSizeEdit")
    public String toSizeEdit(Integer id, HttpServletRequest request, HttpServletResponse response){
        Size size = sizeService.queryById(id);
        request.setAttribute("size", size);
        return "size/sizeEdit";
    }

    @RequestMapping("/sizeEdit")
    @ResponseBody
    public String sizeEdit(Size size, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            count = sizeService.update(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/delSize")
    @ResponseBody
    public String delSize(Integer id, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            List<Integer> ids = new ArrayList<>();
            ids.add(id);
            count = sizeService.delById(ids);
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
            count = sizeService.delById(Arrays.asList(ids));
            if (count == ids.length) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}