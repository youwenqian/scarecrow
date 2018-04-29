package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Size;
import com.shoes.scarecrow.persistence.domain.SizeCondition;
import com.shoes.scarecrow.persistence.service.SizeService;
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
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-25
 * Time 上午1:25
 */
@Controller
@RequestMapping("/size")
public class SizeController {
    private Logger log = Logger.getLogger(SizeController.class);

    @Autowired
    private SizeService sizeService;
    @RequestMapping("allDetail")
    public void getBrandDetailByPage(int page, int limit,String sizeName, HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取品牌信息的方法，limit="+limit+",page="+page+",name="+sizeName);
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        SizeCondition condition = new SizeCondition();
        condition.setSizeName(sizeName);
        condition.setPage(null);
        condition.setPageSize(null);
        condition.setStartRow(null);
        int total = sizeService.queryCountByCondition(condition);
        condition.setPage(page);
        condition.setPageSize(limit);
        int start = (page-1)*limit;
        condition.setStartRow(start);
        List<Size> list = sizeService.queryByCondition(condition);
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

    @RequestMapping("/addSize")
    @ResponseBody
    public Map addSize(HttpSession session,Size size){
        ObjectMapper mapper = new ObjectMapper();
        String userName = (String) session.getAttribute("userName");
        try{
            log.info(session.getAttribute("userName")+"进入添加尺码方法"+mapper.writeValueAsString(size));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = sizeService.saveSize(size);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开添加尺码方法，添加结果影响行数:"+n);
        return map;
    }

    @RequestMapping("/updateSize")
    @ResponseBody
    public Map updateBrand(Size size,HttpSession session){
        ObjectMapper mapper = new ObjectMapper();
        try{
            log.info(session.getAttribute("userName")+"进入修改品牌方法"+mapper.writeValueAsString(size));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = sizeService.updateSize(size);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开修改品牌方法，结果影响行数:"+n);
        return map;
    }
    @RequestMapping("/deleteSize/{id}")
    @ResponseBody
    public Map deleteBrand(@PathVariable("id") String id, HttpSession session){
        log.info(session.getAttribute("userName")+"进入删除品牌方法，删除品牌id="+id);
        int ids = Integer.valueOf(id).intValue();
        int n = sizeService.delSize(ids);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开删除品牌方法，结果影响行数:"+n);
        return map;
    }
}
