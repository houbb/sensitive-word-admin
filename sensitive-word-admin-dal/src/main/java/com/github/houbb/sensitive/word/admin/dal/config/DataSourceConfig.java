package com.github.houbb.sensitive.word.admin.dal.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 *
 * 对于 mapper 的扫描，必须通过 @MapperScan 指定，或者通过 @Mapper 注解指定。
 * @author binbin.hou
 * @since 0.0.1
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.github.houbb.sensitive.word.admin.dal.mapper")
public class DataSourceConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        page.setLocalPage(true);
        return page;
    }

    /**
     * 打印 sql
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }

}
