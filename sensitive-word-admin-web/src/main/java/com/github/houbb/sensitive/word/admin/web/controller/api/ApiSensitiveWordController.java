package com.github.houbb.sensitive.word.admin.web.controller.api;

import com.github.houbb.auto.log.annotation.AutoLog;
import com.github.houbb.auto.log.annotation.TraceId;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.web.common.dto.resp.BaseResp;
import com.github.houbb.web.common.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * api 服务
 * @author binbin.hou
 * @since 1.1.0
 */
@RestController
@RequestMapping("/api/sensitiveWord/")
@AutoLog
@TraceId
public class ApiSensitiveWordController {

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    /**
     * 是否包含敏感词
     *
     * @param text 文本
     * @return 结果
     */
    @RequestMapping("/contains")
    public BaseResp contains(@RequestParam("text") String text) {
       boolean contains = sensitiveWordBs.contains(text);

        return RespUtil.of(contains);
    }

    /**
     * 获取所有的敏感词
     * @param text 文本
     * @return 结果
     */
    @RequestMapping("/findAll")
    public BaseResp findAll(@RequestParam("text") String text) {
        List<String> results = sensitiveWordBs.findAll(text);

        return RespUtil.of(results);
    }

    /**
     * 获取替换后的结果
     *
     * @param text 文本
     * @return 结果
     */
    @RequestMapping("/replace")
    public BaseResp replace(@RequestParam("text") String text) {
        String results = sensitiveWordBs.replace(text);

        return RespUtil.of(results);
    }

}
