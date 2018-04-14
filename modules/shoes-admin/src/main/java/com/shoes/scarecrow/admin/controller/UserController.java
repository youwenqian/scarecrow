package com.shoes.scarecrow.admin.controller;

import com.shoes.scarecrow.admin.common.LoginContext;
import com.shoes.scarecrow.admin.common.WebUtils;
import com.shoes.scarecrow.common.enums.UserType;
import com.shoes.scarecrow.common.utils.MD5;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.service.UserService;
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
 * @create 2018/3/20 20:14
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    protected UserService userService;

    @RequestMapping("/userView")
    public String userView(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> param = WebUtils.postData(request);
        WebUtils.setPage(param,request);
        if (param != null && param.get("isPayment") == -1){
            param.put("isPayment", null);
        }
        List<User> users = userService.queryListByCondition(param);
        int count = userService.getCountByCondition(param);
        request.setAttribute("users", users);
        request.setAttribute("count", count);
        WebUtils.setDataParam(param, request);
        return "user/userList";
    }

//    @RequestMapping(value = "/userList")
//    public String userList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map<String, Object> param = WebUtils.postData(request);
//        WebUtils.setPage(param,request);
//        if (param != null && param.get("isPayment") == -1){
//            param.put("isPayment", null);
//        }
//        List<User> users = userService.queryListByCondition(param);
//        int count = userService.getCountByCondition(param);
//        request.setAttribute("users", users);
//        request.setAttribute("count", count);
//        return "user/userList";
//    }

    @RequestMapping("/toUserAdd")
    public String toUserAdd(HttpServletRequest request, HttpServletResponse response){
        return "user/userAdd";
    }

    @RequestMapping("/userAdd")
    @ResponseBody
    public String userAdd(User user, HttpServletRequest request, HttpServletResponse response){
        int count =0;
        try{
            user.setCreateUser(LoginContext.getUserName(request));
            user.setUpdateUser(LoginContext.getUserName(request));
            user.setPassword(MD5.excute(user.getPassword()));
            user.setStatus(1);
            user.setYn((byte)0);
            user.setUserType(UserType.ADMIN.getKey());
            count = userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/toUserEdit")
    public String toUserEdit(Long id, HttpServletRequest request, HttpServletResponse response){
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/userEdit")
    @ResponseBody
    public String userEdit(User user, HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        try {
            user.setUpdateUser(LoginContext.getUserName(request));
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/delUser")
    @ResponseBody
    public String delUser(Long id, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            List<Long> ids = new ArrayList<>();
            ids.add(id);
            count = userService.delUser(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            return "success";
        } else {
            return "error";
        }
    }
    @RequestMapping("/batchDelUser")
    @ResponseBody
    public String batchDelUser(Long[] ids, HttpServletRequest request, HttpServletResponse response){
        int count = 0;
        try{
            count = userService.delUser(Arrays.asList(ids));
            if (count == ids.length) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}