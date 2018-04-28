package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.service.BrandService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
    public void getBrandDetailByPage(int page, int limit, HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取品牌信息的方法，limit="+limit+",page="+page);
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Brand> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            Brand brand = new Brand();
            brand.setId(i);
            brand.setName("品牌"+i);
            brand.setCreateUser("创建人"+i);
            brand.setCreateTime(new Date());
            brand.setUpdateUser("更新人"+i);
            brand.setUpdateTime(new Date());
            brand.setRemark("备注"+i);
            list.add(brand);
        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        int start = (page-1)*limit;
        int end = page*limit<=list.size()?page*limit:list.size();
        List<Brand> list2 = list.subList(start,end);
        map.put("data",list2);
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
        log.info(session.getAttribute("userName")+"进入添加品牌方法"+mapper.writeValueAsString(brand));
        brandService.saveBrand();
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
