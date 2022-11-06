package com.example.blog_project.mapper;

import com.example.blog_project.model.Blog;
import com.example.blog_project.model.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @title: BlogMapper
 * @Author Xu
 * @Date: 6/11/2022 下午 8:15
 * @Version 1.0
 */
@Mapper
public interface BlogMapper {
    Integer insert(Blog blog);

    List<Blog> selectAll();

    Blog selectById(@Param("blogId") Integer id);

    Integer delete(@Param("blogId") Integer id);

    Integer selectArticles(@Param("userId") Integer userId);

    Integer update(Blog blog);
}