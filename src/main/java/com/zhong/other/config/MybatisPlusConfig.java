package com.zhong.other.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp配置类
 * @Author: zhong
 * @Description: 扫描实体类包，添加分页插件
 */
@Configuration
@MapperScan("com.zhong.mapper")
public class MybatisPlusConfig {

//    插件主体，用来代理其他插件，相当于一个插件的管理中心

    /**
     * 插件管理——添加了分页插件
     * @return 插件管理对象
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
