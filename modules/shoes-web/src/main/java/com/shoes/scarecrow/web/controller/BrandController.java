package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;
import com.shoes.scarecrow.persistence.service.BrandService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-10
 * Time 上午12:41
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    private static Logger log = Logger.getLogger(BrandController.class);
    @Autowired
    private BrandService brandService;
    @RequestMapping("/allDetail")
    public void getBrandDetailByPage(int page, int limit,String name, HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取品牌信息的方法，limit="+limit+",page="+page);
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        BrandCondition condition = new BrandCondition();
        condition.setName(name);
        condition.setPage(null);
        condition.setPageSize(null);
        condition.setStartRow(null);
        int total = brandService.queryCountByCondition(condition);
        condition.setPage(page);
        condition.setPageSize(limit);
        int start = (page-1)*limit;
        condition.setStartRow(start);
        List<Brand> list = brandService.queryByCondition(condition);
//        list = new ArrayList<>();
//        for(int i=0;i<50;i++){
//            Brand brand = new Brand();
//            brand.setId(i);
//            brand.setName("品牌"+i);
//            brand.setCreateUser("创建人"+i);
//            brand.setCreateTime(new Date());
//            brand.setUpdateUser("更新人"+i);
//            brand.setUpdateTime(new Date());
//            brand.setRemark("备注"+i);
//            list.add(brand);
//        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",total);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取品牌信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求brand/allDetail.do 写返回数据时发生错误。"+e.getMessage());
        }
    }

    @RequestMapping("/addBrand")
    @ResponseBody
    public Map addBrand(HttpSession session,Brand brand){
        ObjectMapper mapper = new ObjectMapper();
        String userName = (String) session.getAttribute("userName");
        brand.setCreateUser(userName);
        brand.setCreateTime(new Date());
        brand.setUpdateUser(userName);
        brand.setUpdateTime(new Date());
        brand.setYn(0);
        brand.setStatus(1);
        try{
            log.info(session.getAttribute("userName")+"进入添加品牌方法"+mapper.writeValueAsString(brand));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = brandService.saveBrand(brand);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开添加品牌方法，添加结果影响行数:"+n);
        return map;
    }

    @RequestMapping("/updateBrand")
    @ResponseBody
    public Map updateBrand(Brand brand,HttpSession session){
        ObjectMapper mapper = new ObjectMapper();
        String userName = (String) session.getAttribute("userName");
        brand.setUpdateUser(userName);
        brand.setUpdateTime(new Date());
        try{
            log.info(session.getAttribute("userName")+"进入修改品牌方法"+mapper.writeValueAsString(brand));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = brandService.updateBrand(brand);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开修改品牌方法，结果影响行数:"+n);
        return map;
    }
    @RequestMapping("/deleteBrand/{id}")
    @ResponseBody
    public Map deleteBrand(@PathVariable("id") Integer id, HttpSession session){
        log.info(session.getAttribute("userName")+"进入删除品牌方法，删除品牌id="+id);
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        int n = brandService.delBrand(ids, "");
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开删除品牌方法，结果影响行数:"+n);
        return map;
    }
    @RequestMapping("/shortBrand")
    public void getBrandShortMessage(HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到获取品牌简单信息的方法");
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Brand> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            Brand brand = new Brand();
            brand.setId(i);
            brand.setName("品牌"+i);
            list.add(brand);
        }
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出到获取品牌简单信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求brand/allDetail.do 写返回数据时发生错误。"+e.getMessage());
        }
    }
}
