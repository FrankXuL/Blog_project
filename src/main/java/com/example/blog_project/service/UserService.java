package com.example.blog_project.service;

import com.example.blog_project.mapper.UserMapper;
import com.example.blog_project.model.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: UserService
 * @Author Xu
 * @Date: 6/11/2022 下午 9:02
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Integer insert(user user) {
        return userMapper.insert(user);
    }


    public List<user> selectAll() {
        return userMapper.selectAll();
    }


    public user selectById(@Param("userId") Integer id) {
        return userMapper.selectById(id);
    }

    public Integer delete(@Param("userId") Integer id) {
        return userMapper.delete(id);
    }

    public user selectByName(@Param("username") String username) {
        return userMapper.selectByName(username);
    }
    public user selectByBlogId(@Param("blogId") Integer blogId){
        return userMapper.selectByBlogId(blogId);
    }
    public Integer update (user user){
        return userMapper.update(user);
    }
}