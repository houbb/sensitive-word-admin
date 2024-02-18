package com.github.houbb.sensitive.word.admin.web.config;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;
import com.github.houbb.sensitive.word.admin.service.service.WordLogService;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
@Slf4j
public class MySensitiveWordScheduleRefresh {

    private static final Logger logger = LoggerFactory.getLogger(MySensitiveWordScheduleRefresh.class);

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
        logger.info("MySensitiveWordScheduleRefresh init with refreshIntervalSeconds={}", refreshIntervalSeconds);

        // 单线程定时调度。
        // TODO: 调整对应的 word_log 实现
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("MySensitiveWordScheduleRefresh start");

                    refresh();

                    logger.info("MySensitiveWordScheduleRefresh end");
                } catch (Exception e) {
                    logger.error("MySensitiveWordScheduleRefresh meet ex", e);
                }
            }
        }, refreshIntervalSeconds, refreshIntervalSeconds, TimeUnit.SECONDS);
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
        // 延长10S，避免遗漏
        int timeDiffer = refreshIntervalSeconds + 10;
        // 判断当前一段时间内是否存在变化？
        Date date = DateUtil.addSecond(new Date(), -timeDiffer);

        Wrapper<WordLog> wordLogWrapper = new EntityWrapper<>();
        wordLogWrapper.gt("update_time", date);
        int count = wordLogService.selectCount(wordLogWrapper);
        if(count <= 0) {
            logger.info("MySensitiveWordScheduleRefresh 没有新增的变化信息，忽略更新。");
            return;
        }

        // 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法，然后调用这个方法。
        // 后续可以优化为针对变化的初始化。
        sensitiveWordBs.init();
    }
    
}
