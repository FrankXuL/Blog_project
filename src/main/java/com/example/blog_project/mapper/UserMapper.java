package com.example.blog_project.mapper;

import com.example.blog_project.model.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @title: UserMapper
 * @Author Xu
 * @Date: 6/11/2022 下午 8:08
 * @Version 1.0
 */

@Mapper
public interface UserMapper {
    Integer insert(user user);

    List<user> selectAll();

    user selectById(@Param("userId") Integer id);

    Integer delete(@Param("userId") Integer id);

    user selectByName(@Param("username") String username);

    user selectByBlogId(@Param("blogId") Integer blogId);
    Integer update (user user);
}