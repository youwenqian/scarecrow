package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.web.model.Stock;
import com.shoes.scarecrow.web.service.RegisterService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-3-10
 * Time 下午6:33
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private static Logger log = Logger.getLogger(RegisterController.class);

    @RequestMapping("/forward")
    public String forward(){
        return "register";
    }
    @RequestMapping("/init")
    @ResponseBody
    public Map init(Model model){
        log.info("进入register初始化controller类");
        Map<String,Object> map = new HashMap<String,Object>();
        List<Stock> list = new ArrayList<Stock>();
        list.add(new Stock("1","国外拿货"));
        list.add(new Stock("2","国内拿货"));
        list.add(new Stock("3","淘宝销售"));
        map.put("stockList",list);
//        model.addAllAttributes(map);
        log.info("离开register初始化controller类");
        return  map;
    }
    @RequestMapping(value = "/images", method = RequestMethod.POST)
    @ResponseBody
    public Map images(MultipartFile file, HttpSession session){
        log.info("开始上传微信图片");
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","false");
        String name = file.getOriginalFilename();
        String path = session.getServletContext().getRealPath("/WEB-INF/classes/images/weixin");
        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        Long nowTime = 1l;
        synchronized (nowTime){
            nowTime = new Date().getTime();
        }
        File tempFile = new File(path,String.valueOf(nowTime)+name.substring(name.indexOf(".")));
        String tempFileName = tempFile.getAbsolutePath();
        log.info("临时文件："+tempFileName);
        try {
            file.transferTo(tempFile);
            map.put("code","true");
            map.put("message",tempFileName);
        } catch (IOException e) {
            e.printStackTrace();log.error("上传文件失败"+e.getMessage());
            map.put("message","上传失败，请重新上传");
        }
        log.info("微信图片上传成功！上传目录："+tempFileName);
        return map;
    }
    @RequestMapping(value="/registerUser",method = RequestMethod.POST)
    @ResponseBody
    public Map registerUser(@RequestBody User user, HttpSession session){
        log.info("用户注册");
        String imgUrl = user.getImageAddress();
        File imgFile = new File(imgUrl);
        String imgFileName = imgFile.getParent()+System.getProperty("file.separator")+user.getUserName()+System.getProperty("file.separator")+imgFile.getName();
        log.info("微信图片："+imgFileName);
        File finalImgFile = new File(imgFileName);
        if(!finalImgFile.getParentFile().exists()){
            finalImgFile.getParentFile().mkdirs();
        }
//        finalImgFile.createNewFile();
        imgFile.renameTo(finalImgFile);
        Map<String,String> map = new HashMap<>();
        //插入数据库成功后，返回管理员微信收款二维码
        map.put("code","true");
        String path = session.getServletContext().getRealPath("/WEB-INF/classes/images/weixin");
        String imageFile = path.substring(path.lastIndexOf("WEB-INF")) + "webwxgetmsgimg.jpeg";
        map.put("imageUrl",imageFile);
        return map;
    }
}
