package com.example.blog_project.controller;

import com.example.blog_project.model.Blog;
import com.example.blog_project.model.user;
import com.example.blog_project.service.BlogService;
import com.example.blog_project.util.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @title: BlogController
 * @Author Xu
 * @Date: 6/11/2022 下午 9:08
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    /**
     * @description: 获取指定用户博客列表
     * @param: request,userId
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:36
     */
    @RequestMapping("/getmyblogs")
    public HashMap<String, Object> GetMyBlog(String userId) {
        HashMap<String, Object> result = new HashMap<>();
        List<Blog> data = null;
        String message = "未知错误";
        data = blogService.selectByUserId(Integer.parseInt(userId));
        if(data != null){
            message = "";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
    /**
     * @description: 获取所有博客
     * @param:
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:44
     */
    @RequestMapping("/getblogs")
    public HashMap<String, Object> GetBlogs() {
        HashMap<String, Object> result = new HashMap<>();
        List<Blog> data = null;
        String message = "未知错误";
        data = blogService.selectAll();
        if(data != null){
            message = "";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
    /**
     * @description: 获取指定博客
     * @param: blogId
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:45
     */
    @RequestMapping("/getblog")
    public HashMap<String, Object> GetBlog(String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        Blog blog = null;
        String message = "未知错误";
        blog = blogService.selectByBlogId(Integer.parseInt(blogId));
        if(blog != null){
            message = "";
        }
        result.put("success", 200);
        result.put("data", blog);
        result.put("message", message);
        return result;
    }
}