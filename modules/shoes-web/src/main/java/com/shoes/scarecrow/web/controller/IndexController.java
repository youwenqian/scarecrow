package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.UserService;
import com.shoes.scarecrow.web.model.Stock;
import com.shoes.scarecrow.web.service.IndexService;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author li
 * @description
 * @time 2018/3/8 14:22
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static Logger log = Logger.getLogger(IndexController.class);
    @Resource
    private IndexService indexService;
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public Map login(String userName,String passWord ,HttpSession session){
        log.info("username="+userName+",password="+passWord);
        User user = userService.getUser(userName,passWord);
        Integer userType = user.getUserType();
        session.setAttribute("userName",userName);
        if(0==userType){//guanliyuan
            session.setAttribute("user","manager");
            session.setAttribute("userId",user.getId());
        }else{
            session.setAttribute("user","merchant");
            session.setAttribute("userId",user.getId());
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",true);
        map.put("message","登录成功");
        return map;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try{
//            String retStr = objectMapper.writeValueAsString(map);
//            response.setCharacterEncoding("utf-8");
//            PrintWriter printWriter = response.getWriter();
//            printWriter.print(retStr);
//        }catch (IOException e){
//            log.error("请求index/login.do 写返回数据时发生错误。"+e.getMessage());
//        }
//        boolean checkUserFlag = indexService.login(userName,passWord);
//        return "true";//+checkUserFlag;
    }
    @RequestMapping("/logout")
    public void logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.removeAttribute("userName");
        log.info(request.getRequestURI());
        String basePath = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getServletPath()));
        log.info(request.getRequestURL());
        log.info(request.getServletPath());
        response.sendRedirect(basePath);
    }
    @RequestMapping("/main")
    public ModelAndView main(HttpSession session){
        String retCode = null;
        if(session.getAttribute("user").equals("manager")){
            retCode = "managerMain";
        }else{
            retCode = "main";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(retCode);
        modelAndView.addObject("userName",session.getAttribute("userName"));
        return modelAndView;
    }
    @RequestMapping("/register")
    public String register(){
        return "redirect:/register/forward";
    }
    @RequestMapping(value = "/register/images", method = RequestMethod.POST)
    public void images(MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception {
        log.info("开始上传微信图片");
        String name = file.getOriginalFilename();
        log.info(name);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // Set factory constraints
        factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
        String tempPathFile = System.getProperty("java.io.tmpdir");
        factory.setRepository(new File(tempPathFile));// 设置缓冲区目录
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println(tempPathFile);
        //如果文件不为空，写入上传路径

        //上传文件路径
        String path = request.getServletContext().getRealPath("/images/weixin/");//"/home/youwenqian/image/";//
        //上传文件名
        upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
        File tempFile = new File(path,name);
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();
        }
        if(!tempFile.exists()){
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        System.out.print("上传成功！");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",true);
        map.put("message","上传成功");
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求index/register/images.do 写返回数据时发生错误。"+e.getMessage());
        }
    }
}
