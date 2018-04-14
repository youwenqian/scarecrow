package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.common.utils.Constants;
import com.shoes.scarecrow.common.utils.MD5;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/12 20:55
 */
@Controller
public class AdminController {
    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response){
        long tid = System.nanoTime();
        LOGGER.info("tid:{} 登录 userName:{} ", tid, userName);
        try{
            User user = userService.getUserByName(userName, MD5.excute(password));
            if(user == null || user.getStatus() == 0){
                request.setAttribute("code", -905);
                return "login";
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(user.getId() + "", user);
            WebUtils.setSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY,user);
            request.setAttribute("user", user);
            return "index";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("code", -1);
        }
        return "login";
    }
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        return "welcome";
    }
}