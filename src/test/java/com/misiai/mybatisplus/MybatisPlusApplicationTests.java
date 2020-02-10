package com.misiai.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.misiai.mybatisplus.bean.User;
import com.misiai.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisPlusApplicationTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @Test
    void test01() throws SQLException {
        System.out.println("dataSource = " + dataSource);
        System.out.println(dataSource.getConnection());
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println("user = " + user);
        }


    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(12);
        user.setMail("admin@misiai.com");
        user.setPassword("12413");
        user.setUser_name("无道");
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1);
        user.setAge(20);
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdateByCondition() {
        User user = new User();
        user.setAge(20);
        user.setUser_name("无道道");
        user.setPassword("ewjalfdif");

        // 查询条件的更新
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_name", "张三");

        int update = userMapper.update(user, queryWrapper);
        System.out.println("update = " + update);


    }

    @Test
    public void testUpdateByCondition2() {
        User user = new User();
        user.setAge(21);
        user.setUser_name("无道");
        user.setPassword("e21312dif");

        // 查询条件的更新
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

        wrapper.set("age", 21).set("password", "999999")//更新字段
                .eq("user_name", "无道道");//更新条件

        int update = userMapper.update(user, wrapper);
        System.out.println("update = " + update);


    }

    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(11);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "无道");
        map.put("age", 21);

        int i = userMapper.deleteByMap(map);
        System.out.println("i = " + i);
    }

    @Test
    public void testDeleteWrap() {
        // QueryWrapper<User> wrapper = new QueryWrapper<>();
        // wrapper.eq("user_name", "无道");


        // 用法二
        User user = new User();
        user.setAge(21);
        user.setUser_name("无道");
        user.setPassword("e21312dif");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        int delete = userMapper.delete(wrapper);
        System.out.println("delete = " + delete);

    }

    @Test
    public void testDeleteBatchId() {
        int i = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.out.println("i = " + i);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("user_name", "赵六");
        User user = userMapper.selectOne(wrapper);
        // 当我们查询的数据，实际有多条时，会报错！！
        System.out.println("user = " + user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println("integer = " + integer);
    }

    @Test
    public void testSelectList() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.like("password", "8");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println("users = " + users);

    }

    @Test
    public void testSelectPage() {
        int current = 2;//当前页
        int row = 1;// 每页条数
        Page<User> page = new Page<>(current, row);


        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.like("password", "8");

        Page<User> selectPage = userMapper.selectPage(page, wrapper);

        System.out.println("selectPage.getSize() = " + selectPage.getSize());
        System.out.println("selectPage.getTotal() = " + selectPage.getTotal());
        System.out.println("selectPage.getOrders() = " + selectPage.getOrders());
        System.out.println("selectPage.getCurrent() = " + selectPage.getCurrent());
        System.out.println("selectPage.getRecords() = " + selectPage.getRecords());

    }

    @Test
    public void testFindById() {
        // User byId = userMapper.findById(1);
        // System.out.println("byId = " + byId);
    }

    @Test
    public void testAllEq() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("password", "8989");
        map.put("age", "28");

        wrapper.allEq(map);

        List<User> users = userMapper.selectList(wrapper);
        System.out.println("users = " + users);
    }

    @Test
    public void testLike() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "五");
        List<User> users = userMapper.selectList(wrapper);

        System.out.println("users = " + users);
    }

    @Test
    public void testOrder() {


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // wrapper.orderByAsc();
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);

        System.out.println("users = " + users);
    }

    @Test
    public void testOr() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("user_name", "赵六").or().eq("user_name", "孙七");

        List<User> users = userMapper.selectList(wrapper);

        System.out.println("users = " + users);
    }

    @Test
    public void testSelect() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // select 指定查询字段
        wrapper.eq("user_name", "赵六").or().eq("user_name", "孙七").select("id", "user_name");

        List<User> users = userMapper.selectList(wrapper);

        System.out.println("users = " + users);
    }

    @Test
    public void test() {

    }
}
