package com.example.blog_project.mapper;

import com.example.blog_project.model.user;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Transactional
    @Test
    void insert() {
        user user = new user();
        user.setUsername("aaa");
        user.setPassword("123");
        user.setGithub("www.GitHub.com");
        log.info("insert:" + userMapper.insert(user));
    }

    @Test
    void selectAll() {
        List<user> list = userMapper.selectAll();
        log.info("selectAll" + list);
    }

    @Test
    void selectById() {
        user user = userMapper.selectById(1);
        log.info("selectById:" + user);
    }

    @Transactional
    @Test
    void delete() {
        log.info("delete:" + userMapper.delete(1));
    }

    @Test
    void selectByName() {
        user user = userMapper.selectByName("oasis");
        log.info("selectByName" + user);
    }
}