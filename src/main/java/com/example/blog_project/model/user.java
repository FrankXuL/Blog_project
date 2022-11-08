package com.example.blog_project.model;

import lombok.Data;

/**
 * @title: user
 * @Author Xu
 * @Date: 6/11/2022 下午 8:05
 * @Version 1.0
 */
@Data
public class user {
    private int userId;
    private String username;
    private String password;
    private String github;
}