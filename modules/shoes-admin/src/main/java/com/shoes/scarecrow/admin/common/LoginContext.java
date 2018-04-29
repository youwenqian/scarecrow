package com.shoes.scarecrow.admin.common;

import com.shoes.scarecrow.common.utils.Constants;
import com.shoes.scarecrow.persistence.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 16:31
 */
public class LoginContext {

    public static User getUser(HttpServletRequest request){
        User user = (User) org.springframework.web.util.WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
        return user;
    }

    public static String getUserName(HttpServletRequest request){
        User user = (User) org.springframework.web.util.WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
        return user.getUserName();
    }

}