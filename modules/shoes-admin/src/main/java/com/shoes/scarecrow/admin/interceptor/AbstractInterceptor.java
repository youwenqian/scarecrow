package com.shoes.scarecrow.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/16 20:35
 */
public abstract class AbstractInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession().getAttribute("");
        String context = request.getContextPath();
        String protocol = request.getScheme();
        Integer port = request.getServerPort();
        String name = request.getServerName();
        String root = protocol + "://" + name + ":" + (port == null?80:port) + context + "/";
        request.setAttribute("root", root);
        return innerPreHandle(request, response, handler);
    }

    protected abstract boolean innerPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }
}