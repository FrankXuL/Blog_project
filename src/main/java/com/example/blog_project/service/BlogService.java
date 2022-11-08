package com.example.blog_project.service;

import com.example.blog_project.mapper.BlogMapper;
import com.example.blog_project.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: BlogService
 * @Author Xu
 * @Date: 6/11/2022 下午 9:05
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;


    public Integer insert(Blog blog) {
        return blogMapper.insert(blog);
    }

    public List<Blog> selectAll() {
        return blogMapper.selectAll();
    }

    public List<Blog> selectByUserId(@Param("blogId") Integer id) {
        return blogMapper.selectByUserId(id);
    }

    public Integer delete(@Param("blogId") Integer id) {
        return blogMapper.delete(id);
    }

    public Integer selectArticles(@Param("userId") Integer userId) {
        return blogMapper.selectArticles(userId);
    }

    public Integer update(Blog blog) {
        return blogMapper.update(blog);
    }

    public Blog selectByBlogId(@Param("blogId") Integer id) {
        return blogMapper.selectByBlogId(id);
    }

    public Integer updateReadCount(Integer blogId){
        return blogMapper.updateReadCount(blogId);
    }
}