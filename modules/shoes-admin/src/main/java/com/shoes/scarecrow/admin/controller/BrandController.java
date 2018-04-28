package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.LoginContext;
import com.shoes.scarecrow.admin.common.WebUtils;
import com.shoes.scarecrow.common.utils.DateUtils;
import com.shoes.scarecrow.common.utils.MD5;
import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.BrandService;
import org.apache.commons.lang3.StringUtils;
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
 * @create 2018/4/9 20:31
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/brandList")
    public String brandList(HttpServletRequest request, HttpServletResponse response){
//        Map<String, Object> param = WebUtils.postData(request);
//        WebUtils.setPage(param,request);
        try{
            BrandCondition condition = new BrandCondition();
            if(StringUtils.isNotEmpty(request.getParameter("name")))
                condition.setName(request.getParameter("name"));
            if(StringUtils.isNotEmpty(request.getParameter("createTime")))
                condition.setCreateTime(DateUtils.parse(request.getParameter("createTime")));
            if(StringUtils.isNotEmpty(request.getParameter("endTime")))
                condition.setEndTime(DateUtils.parse(request.getParameter("endTime")));
            List<Brand> brands = brandService.queryByCondition(condition);
            int count = brandService.queryCountByCondition(condition);
            request.setAttribute("brands", brands);
            request.setAttribute("count", count);
            request.setAttribute("condition", condition);
            return "brand/brandList";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/toBrandAdd")
    public String toBrandAdd(HttpServletRequest request, HttpServletResponse response){
        return "brand/brandAdd";
    }

    @RequestMapping("/brandAdd")
    @ResponseBody
    public String brandAdd(Brand brand, HttpServletRequest request, HttpServletResponse response){
        int count =0;
        try{
            brand.setCreateUser(LoginContext.getUserName(request));
            brand.setUpdateUser(LoginContext.getUserName(request));
            brand.setStatus(1);
            brand.setYn(0);
            count = brandService.saveBrand(brand);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/toBrandEdit")
    public String toBrandEdit(Integer id, HttpServletRequest request, HttpServletResponse response){
        Brand brand = brandService.queryById(id);
        request.setAttribute("brand", brand);
        return "brand/brandEdit";
    }

    @RequestMapping("/brandEdit")
    @ResponseBody
    public String brandEdit(Brand brand, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            brand.setUpdateUser(LoginContext.getUserName(request));
            brand.setUpdateTime(new Date());
            count = brandService.updateBrand(brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/delBrand")
    @ResponseBody
    public String delBrand(Integer id, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            String updateUser = LoginContext.getUserName(request);
            List<Integer> ids = new ArrayList<>();
            ids.add(id);
           count = brandService.delBrand(ids, updateUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }
    @RequestMapping("/batchDelBrand")
    @ResponseBody
    public String batchDelBrand(Integer[] ids, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            String updateUser = LoginContext.getUserName(request);
            count = brandService.delBrand(Arrays.asList(ids), updateUser);
            if (count == ids.length) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}