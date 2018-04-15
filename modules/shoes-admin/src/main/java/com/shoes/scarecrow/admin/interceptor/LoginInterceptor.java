package com.shoes.scarecrow.admin.interceptor;

import com.shoes.scarecrow.common.utils.Constants;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/14 14:09
 */
public class LoginInterceptor extends AbstractInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    protected boolean innerPreHandle(HttpServletRequest request,
                                     HttpServletResponse response, Object handler) throws Exception {

        User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
        if (user != null) {//已登录
            request.setAttribute("indexUser", user);
            return true;
        } else{
            response.sendRedirect("/index");
            return false;
        }

    }
}