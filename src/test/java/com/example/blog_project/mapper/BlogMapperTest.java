package com.example.blog_project.mapper;

import com.example.blog_project.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
@Slf4j
class BlogMapperTest {
    @Resource
    private BlogMapper blogMapper;

    @Test
    void insert() {
        Blog blog = new Blog();
        blog.setTitle("hello");
        blog.setContent("hello world");
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        log.info("insert:" + blogMapper.insert(blog));
    }

    @Test
    void selectAll() {
        List<Blog> list = blogMapper.selectAll();
        for(Blog blog : list){
            log.info("selectAll" + blog);
        }
    }

    @Test
    void selectById() {
        Blog blog = blogMapper.selectById(1);
        log.info("selectById:" + blog);
    }
    @Transactional
    @Test
    void delete() {
        log.info("delete:" + blogMapper.delete(1));
    }

    @Test
    void selectArticles() {
        log.info("selectArticles:" + blogMapper.selectArticles(1));
    }

    @Test
    void update() {
        Blog blog = new Blog();
        blog.setBlogId(1);
        blog.setTitle("hello");
        blog.setContent("hello world2");
        log.info("update:" + blogMapper.update(blog));
    }
}