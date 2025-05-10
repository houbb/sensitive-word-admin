package com.github.houbb.system.sensitive;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.system.sensitive.support.MyDdWordAllow;
import com.github.houbb.system.sensitive.support.MyDdWordDeny;
import com.github.houbb.system.sensitive.support.MyDdWordTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
@EnableScheduling
public class SensitiveWordConfigSchedule {

    private static final Logger log = LoggerFactory.getLogger(SensitiveWordConfigSchedule.class);

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    @Autowired
    private MyDdWordTags myDdWordTags;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES, fixedDelay = 5)
    public void refresh(){
        log.info(">>>>>>>>>>>>>> refresh start");
        myDdWordTags.init();
        sensitiveWordBs.init();
        log.info(">>>>>>>>>>>>>> refresh end");
    }

}
