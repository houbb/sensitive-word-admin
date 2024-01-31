package com.github.houbb.sensitive.word.admin.web.config;

import com.github.houbb.sensitive.word.admin.service.service.WordLogService;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.HelloWorld;

import javax.annotation.PostConstruct;

/**
 * 分布式部署的更新问题：
 *
 * 模式1：push
 * 实时性好，但是需要感知系统的存在。
 *
 * 模式2：pull
 * 存在延迟，但是无状态，简单。
 *
 * 这里采用模式2
 *
 * @since 1.2.0
 */
@Component
public class MySensitiveWordScheduleRefresh {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    @Autowired
    private WordLogService wordLogService;

    /**
     * 刷新时间间隔
     * @since 1.3.0
     */
    @Value("${sensitive-word.refresh-interval-seconds}")
    private int refreshIntervalSeconds;

    @PostConstruct
    public void init() {
        // 单线程定时调度。
        // TODO: 调整对应的 word_log 实现
    }

    /**
     * 更新词库
     *
     * 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法。
     * 如果需要生效，则调用这个方法。
     *
     * 说明：重新初始化不影响旧的方法使用。初始化完成后，会以新的为准。
     */
    private void refresh() {
        // 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法，然后调用这个方法。
        sensitiveWordBs.init();
    }
    
}
