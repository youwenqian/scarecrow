package com.shoes.scarecrow.admin.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/2 22:02
 */
public class WebUtils {

    public static Map<String,Object> postData(HttpServletRequest req){
        Map<String,Object> params = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String val = req.getParameter(name);
            if(!StringUtils.isEmpty(val)){
                params.put(name, val);
            }
        }
        return params;
    }

    public static void setDataParam( Map<String, Object> param, HttpServletRequest req){
      if(param == null || req == null) return ;
      Set<String> keys = param.keySet();
      for(String key : keys){
          req.setAttribute(key, param.get(key));
      }
    }

    public static void setPage(Map<String,Object> param,HttpServletRequest request){
        param.put("pageSize",15);
        param.put("page",request.getParameter("page"));
        Integer page = request.getParameter("page") == null ? 1:Integer.valueOf(request.getParameter("page").toString());
        param.put("startRow",15*(page-1));
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }
}