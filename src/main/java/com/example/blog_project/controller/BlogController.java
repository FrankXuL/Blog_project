package com.example.blog_project.controller;

import com.example.blog_project.model.Blog;
import com.example.blog_project.model.user;
import com.example.blog_project.service.BlogService;
import com.example.blog_project.util.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
     * @param: request, userId
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:36
     */
    @RequestMapping("/getmyblogs")
    public HashMap<String, Object> GetMyBlog(String userId) {
        HashMap<String, Object> result = new HashMap<>();
        List<Blog> data = null;
        String message = "未知错误";
        data = blogService.selectByUserId(Integer.parseInt(userId));
        if (data != null) {
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
     * @description: 获取所有博客
     * @param:
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:44
     */
    @RequestMapping("/getblogs")
    public HashMap<String, Object> GetBlogs() {
        HashMap<String, Object> result = new HashMap<>();
        List<Blog> data = null;
        String message = "未知错误";
        data = blogService.selectAll();
        if (data != null) {
            message = "";
        } else {
            message = "非法参数";
        }
        int num = data.size();
        result.put("success", 200);
        result.put("data", data);
        result.put("number",num);
        result.put("message", message);
        return result;
    }

    /**
     * @description: 获取指定博客
     * @param: blogId
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 7/11/2022 下午 8:45
     */
    @RequestMapping("/getblog")
    public HashMap<String, Object> GetBlog(String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        Blog blog = null;
        String message = "未知错误";
        blog = blogService.selectByBlogId(Integer.parseInt(blogId));
        if (blog != null) {
            message = "";
        } else {
            message = "非法参数";
        }
        result.put("success", 200);
        result.put("data", blog);
        result.put("message", message);
        return result;
    }

    /**
     * @description: 阅读量增加
     * @param: blogId
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 5:52
     */
    @RequestMapping("/updatereadcount")
    public HashMap<String, Object> updateReadCount(String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        int affect = blogService.updateReadCount(Integer.parseInt(blogId));
        if (affect == 1) {
            data = 1;
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
     * @description: 删除博客
     * @param: blogId
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 5:57
     */
    @RequestMapping("/deleteblog")
    public HashMap<String, Object> DeleteBlog(String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        int affect = blogService.delete(Integer.parseInt(blogId));
        if (affect == 1) {
            data = 1;
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
     * @description: 修改博客
     * @param: blogId, title, content
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 6:32
     */
    @RequestMapping(value = "/update")
    public HashMap<String, Object> UpdateBlog(String blogId, String title, String content) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        Blog blog = new Blog();
        blog.setBlogId(Integer.parseInt(blogId));
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        int affect = blogService.update(blog);
        if (affect == 1) {
            data = 1;
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
     * @description: 插入博客
     * @param: title, content
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 8:16
     */
    @RequestMapping(value = "/insert")
    public HashMap<String, Object> InsertBlog(HttpServletRequest request,String title, String content) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        user user = UserUtil.checkLoginStatus(request);
        blog.setUserId(user.getUserId());
        int affect = blogService.insert(blog);
        if (affect == 1) {
            data = 1;
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
     * @description: 是否为当前用户的文章
     * @param: userId，blogId
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 8/11/2022 下午 8:33
     */
    @RequestMapping("/isyourblog")
    public HashMap<String, Object> IsYourBlog(String userId, String blogId) {
        HashMap<String, Object> result = new HashMap<>();
        int data = -1;
        String message = "未知错误";
        Blog blog = blogService.selectByBlogId(Integer.parseInt(blogId));
        if (blog != null) {
            message = "";
            if (blog.getUserId() == Integer.parseInt(userId)) {
                data = 1;
            } else {
                data = 2;
            }
        } else {
            message = "非法参数";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
}