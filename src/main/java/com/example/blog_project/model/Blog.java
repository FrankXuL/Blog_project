package com.example.blog_project.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @title: Blog
 * @Author Xu
 * @Date: 6/11/2022 下午 8:04
 * @Version 1.0
 */
@Data
public class Blog {
    private int blogId;
    private String title;
    private String content;
    private Timestamp postTime;
    private int userId;
}