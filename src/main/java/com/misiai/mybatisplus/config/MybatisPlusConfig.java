package com.misiai.mybatisplus.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.misiai.mybatisplus.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan("com.misiai.mybatisplus.mapper")
public class MybatisPlusConfig {

    // 配置分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Bean//sql分析插件
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> iSqlParsers = new ArrayList<>();
        iSqlParsers.add(new BlockAttackSqlParser());//全表更新删除阻断器
        sqlExplainInterceptor.setSqlParserList(iSqlParsers);
        return sqlExplainInterceptor;
    }
}
