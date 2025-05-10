package com.github.houbb.system.sensitive;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.system.sensitive.support.MyDdWordAllow;
import com.github.houbb.system.sensitive.support.MyDdWordDeny;
import com.github.houbb.system.sensitive.support.MyDdWordTags;
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
     * 自定义单词标签
     *
     * @since v1.4.0
     */
    @Autowired
    private MyDdWordTags myDdWordTags;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        myDdWordTags.init();

        return SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.defaults(), myDdWordAllow))
                .wordDeny(WordDenys.chains(WordDenys.defaults(), myDdWordDeny))
                .wordTag(myDdWordTags)
                // 各种其他配置
                .init();
    }


}
