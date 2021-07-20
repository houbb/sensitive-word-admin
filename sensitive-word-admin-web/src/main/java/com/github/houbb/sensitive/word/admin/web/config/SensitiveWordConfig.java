package com.github.houbb.sensitive.word.admin.web.config;

import com.github.houbb.sensitive.word.admin.service.support.sensitive.MyDdWordAllow;
import com.github.houbb.sensitive.word.admin.service.support.sensitive.MyDdWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
@Configuration
public class SensitiveWordConfig {

    @Autowired
    private MyDdWordAllow myDdWordAllow;

    @Autowired
    private MyDdWordDeny myDdWordDeny;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system(), myDdWordAllow))
                .wordDeny(WordDenys.chains(WordDenys.system(), myDdWordDeny))
                .ignoreRepeat(false)
                // 各种其他配置
                .init();
    }

}
