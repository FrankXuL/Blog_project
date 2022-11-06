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

    public Integer insert(Blog blog){
        return blogMapper.insert(blog);
    }

    public List<Blog> selectAll(){
        return blogMapper.selectAll();
    }

    public Blog selectById(@Param("blogId") Integer id){
        return blogMapper.selectById(id);
    }

    public Integer delete(@Param("blogId") Integer id){
        return blogMapper.delete(id);
    }

    public Integer selectArticles(@Param("userId") Integer userId){
        return blogMapper.selectArticles(userId);
    }

    public Integer update(Blog blog){
        return blogMapper.update(blog);
    }
}