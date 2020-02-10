package com.misiai.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model<User> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String user_name;

    // 查询是不显示该字段的值
    @TableField(select = false,fill = FieldFill.INSERT)
    private String password;

    @TableField("email") // 指定数据库中实际的字段名
    private String mail;
    private Integer age;

    @TableField(exist = false) // 在数据库中不存在
    private String address;
}
