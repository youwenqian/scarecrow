package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.WebUtils;
import com.shoes.scarecrow.persistence.domain.Message;
import com.shoes.scarecrow.persistence.service.MessageServcie;
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
 * @create 2018/4/9 20:32
 */
@Controller
@RequestMapping("/message")
public class AdvertController{

    @Autowired
    private MessageServcie messageService;

    @RequestMapping("/messageList")
    public String messageList(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> param = WebUtils.postData(request);
        WebUtils.setPage(param,request);
        try{
            List<Message> messages = messageService.selectByMap(param);
            int count = messageService.selectCountByMap(param);
            request.setAttribute("messages", messages);
            request.setAttribute("count", count);
            WebUtils.setDataParam(param, request);
            return "message/messageList";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/toMessageAdd")
    public String toMessageAdd(HttpServletRequest request, HttpServletResponse response){
        return "message/messageAdd";
    }

    @RequestMapping("/messageAdd")
    @ResponseBody
    public String messageAdd(Message message, HttpServletRequest request, HttpServletResponse response){
        int count =0;
        try{
            count = messageService.save(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/toMessageEdit")
    public String toMessageEdit(Integer id, HttpServletRequest request, HttpServletResponse response){
        Message message = messageService.queryById(id);
        request.setAttribute("message", message);
        return "message/messageEdit";
    }

    @RequestMapping("/messageEdit")
    @ResponseBody
    public String messageEdit(Message message, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            count = messageService.update(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/delMessage")
    @ResponseBody
    public String delMessage(Integer id, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            List<Integer> ids = new ArrayList<>();
            ids.add(id);
            count = messageService.delById(ids);
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
            count = messageService.delById(Arrays.asList(ids));
            if (count == ids.length) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }







}