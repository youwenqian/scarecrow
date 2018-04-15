package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.LoginContext;
import com.shoes.scarecrow.common.utils.Constants;
import com.shoes.scarecrow.common.utils.MD5;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/12 20:55
 */
@Controller
public class LoginController {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        User user = LoginContext.getUser(request);
        if(user == null) return "login";
        request.setAttribute("user", user);
        return "index";
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
            return "redirect:index";
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

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession(false)!=null && request.getSession().getAttribute(Constants.ADMIN_SESSION_USER_KEY)!=null){
            request.getSession().invalidate();
        }
        try {
            response.sendRedirect(request.getContextPath()+"/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/changePwd")
    public String changePwd(Long id,HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("id", id);
        return "changePwd";
    }

    @RequestMapping("/valdatePassword")
    @ResponseBody
    public String valdatePassword(Long id,String password,HttpServletRequest request, HttpServletResponse response){
        User user = userService.getUserById(id);
        if(user != null){
            if(MD5.excute(password).equals(user.getPassword())){
                return "success";
            }
        }
        return "error";
    }
    @RequestMapping("modifyPwd")
    @ResponseBody
    public String modifyPwd(Long id,String oldpass, String newpass, HttpServletRequest request, HttpServletResponse response){
        String userName = LoginContext.getUserName(request);
        User user = userService.getUserById(id);
        if(user != null){
            if(MD5.excute(oldpass).equals(user.getPassword())){
                int count = userService.updatePass(id,MD5.excute(newpass), userName);
                if(count == 1) return "success";
            }
        }
        return "error";
    }
}