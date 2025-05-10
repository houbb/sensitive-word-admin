package com.github.houbb.system.controller.api;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.web.common.dto.resp.BaseResp;
import com.github.houbb.web.common.util.RespUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 敏感词API接口（Swagger增强）
 * @author binbin.hou
 * @since 1.1.0
 */
@RestController
@RequestMapping("/api/sensitiveWord/")
@Api("敏感词相关接口")
public class ApiSensitiveWordController {

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    /**
     * 是否包含敏感词
     *
     * @param text 文本
     * @return 结果
     */
    @GetMapping("/contains")
    @ApiOperation("检查文本是否包含敏感词")
    @ApiImplicitParam(name = "text", value = "待检测文本", required = true, dataType = "string", paramType = "query")
    public BaseResp contains(@RequestParam("text") String text) {
        boolean contains = sensitiveWordBs.contains(text);
        return RespUtil.of(contains);
    }

    /**
     * 获取所有的敏感词
     * @param text 文本
     * @return 结果
     */
    @GetMapping("/findAll")
    @ApiOperation("获取文本中所有匹配的敏感词")
    @ApiImplicitParam(name = "text", value = "待检测文本", required = true, dataType = "string", paramType = "query")
    public BaseResp findAll(@RequestParam("text") String text) {
        List<String> results = sensitiveWordBs.findAll(text);
        return RespUtil.of(results);
    }

    /**
     * 获取第一个的敏感词
     * @param text 文本
     * @return 结果
     * @since 1.2.0
     */
    @GetMapping("/findFist")
    @ApiOperation("获取文本中第一个匹配的敏感词")
    @ApiImplicitParam(name = "text", value = "待检测文本", required = true, dataType = "string", paramType = "query")
    public BaseResp findFist(@RequestParam("text") String text) {
        String result = sensitiveWordBs.findFirst(text);
        return RespUtil.of(result);
    }

    /**
     * 获取敏感词的标签列表
     *
     * @param text 文本
     * @return 结果
     * @since 1.2.0
     */
    @GetMapping("/tags")
    @ApiOperation("获取文本中敏感词对应的标签集合")
    @ApiImplicitParam(name = "text", value = "待检测文本", required = true, dataType = "string", paramType = "query")
    public BaseResp tags(@RequestParam("text") String text) {
        Set<String> tags = sensitiveWordBs.tags(text);
        return RespUtil.of(tags);
    }

    /**
     * 获取替换后的结果
     *
     * @param text 文本
     * @return 结果
     */
    @GetMapping("/replace")
    @ApiOperation("将文本中的敏感词替换为指定符号")
    @ApiImplicitParam(name = "text", value = "待处理文本", required = true, dataType = "string", paramType = "query")
    public BaseResp replace(@RequestParam("text") String text) {
        String result = sensitiveWordBs.replace(text);
        return RespUtil.of(result);
    }
}
