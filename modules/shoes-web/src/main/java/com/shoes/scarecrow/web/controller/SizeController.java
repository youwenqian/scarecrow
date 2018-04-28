package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Size;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("allDetail")
    public void getAllSizeDetailByPage(HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取尺码信息的方法");
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Size> list = new ArrayList<Size>();
        for(int i=0;i<8;i++){
            Size size = new Size();
            size.setId(i);
            size.setSizeName("尺码"+i);
            size.setDesc("备注");
            list.add(size);
        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",8);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取尺码信息的方法，result="+mapper.writeValueAsString(map));
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
            log.error("请求size/allDetail.do 写返回数据时发生错误。"+e.getMessage());
        }
    }
}
