package com.example.blog_project.controller;

import com.example.blog_project.model.user;
import com.example.blog_project.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @title: UserController
 * @Author Xu
 * @Date: 6/11/2022 下午 9:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public HashMap<String, Object> login(HttpServletRequest request, String username, String password) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            user user = userService.selectByName(username);
            if (user != null && user.getPassword().equals(password) && user.getUserId() > 1) {
                data = 1;
                message = "";
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

            } else {
                message = "非法参数";
            }
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

    @RequestMapping("/logout")
    public HashMap<String, Object> logout(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        HttpSession session = request.getSession(false);
        if (session != null) {
            data = 1;
            message = "";
            session.removeAttribute("user");
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

    @RequestMapping("/register")
    public HashMap<String, Object> register(String username, String password) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            user user = new user();
            user.setUsername(username);
            user.setPassword(password);
            int sqlResult = userService.insert(user);
            if (sqlResult == 1) {
                data = 1;
                message = "";
            } else {
                message = "非法参数";
            }
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

}