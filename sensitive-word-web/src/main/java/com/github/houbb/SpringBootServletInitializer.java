package com.github.houbb;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * web容器中进行部署
 * 
 * @author ruoyi
 */
public class SpringBootServletInitializer extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(SpringBootApplication.class);
    }
}
