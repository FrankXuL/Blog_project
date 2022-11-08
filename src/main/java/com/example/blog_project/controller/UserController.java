package com.example.blog_project.controller;

import com.example.blog_project.model.user;
import com.example.blog_project.service.UserService;
import com.example.blog_project.util.UserUtil;
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
@SuppressWarnings(("all"))
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @description:
     * @param: request, username, password
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 12:28
     */
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

    /**
     * @description: 用户注销功能
     * @param: request
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 12:27
     */
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

    /**
     * @description: 用户注册功能
     * @param: username, password
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 12:28
     */
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

    @RequestMapping("/loginstate")
    public HashMap<String, Object> LoginState(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        user data = null;
        String message = "未知错误";
        user user = UserUtil.checkLoginStatus(request);
        if (user != null) {
            data = user;
            message = "";

        } else {
            message = "非法参数";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

    /**
     * @description: 通过BlogId获取用户
     * @param: blogId
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 5:42
     */
    @RequestMapping("/getuserbyblogid")
    public HashMap<String, Object> GetUserByBlogId(String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        user data = null;
        String message = "未知错误";
        user user = userService.selectByBlogId(Integer.parseInt(blogId));
        if (user != null) {
            data = user;
            message = "";
        } else {
            message = "非法参数";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
    /**
     * @description:  修改用户信息
     * @param: request,username,password,gitlink
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 10:13
     */
    @RequestMapping("/update")
    public HashMap<String, Object> UpdateUser(HttpServletRequest request, String username, String password, String gitlink) {
        HashMap<String, Object> result = new HashMap<>();
        user data = null;
        String message = "未知错误";
        user user = UserUtil.checkLoginStatus(request);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setGithub(gitlink);
            int affect = userService.update(user);
            if (affect == 1) {
                data = user;
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