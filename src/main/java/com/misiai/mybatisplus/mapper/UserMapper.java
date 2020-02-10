package com.misiai.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.misiai.mybatisplus.bean.User;
import org.springframework.stereotype.Repository;

public interface UserMapper  extends BaseMapper<User> {
    User findById(Integer id);
}
