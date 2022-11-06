package com.example.blog_project.util;

import com.example.blog_project.model.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @title: UserUtil
 * @Author Xu
 * @Date: 6/11/2022 下午 8:06
 * @Version 1.0
 */
//判定当前是否处于登录状态
@SuppressWarnings({"all"})
public class UserUtil {
    public static user checkLoginStatus(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        user user = (user) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        return user;
    }
}