package com.misiai.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.misiai.mybatisplus.bean.User;
import com.misiai.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActiveRecord {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectById() {
        User user = new User();
        user.setId(4);
        User user1 = user.selectById(); // 在这个位置直接执行selectById

        System.out.println("user1 = " + user1);

    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setUser_name("无道54");
        u.setMail("991432@qq.com");
        u.setAge(22);
        // 这里没写密码
        boolean insert = u.insert();
        System.out.println("insert = " + insert);
    }

    @Test
    public void testUpdate() {
        User u = new User();
        u.setAge(31);
        boolean update = u.update(null);// null代表全表操作
        System.out.println("update = " + update);
    }

    @Test
    public void testDelete() {

        User u = new User();
        u.setId(4);
        boolean b = u.deleteById();
        System.out.println("b = " + b);
    }

    @Test
    public void testSelectCondition() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.ge("age", 30);
        User u = new User();
        List<User> users = u.selectList(wrapper);
        System.out.println("users = " + users);
    }

    @Test
    public void testSelect() {

    }
    @Test
    public void test() {
        User byId = userMapper.findById(12
        );
        System.out.println("byId = " + byId);
    }
}
