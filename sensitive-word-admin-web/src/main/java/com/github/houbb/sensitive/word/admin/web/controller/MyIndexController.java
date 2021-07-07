package com.github.houbb.sensitive.word.admin.web.controller;


import com.github.houbb.auto.log.annotation.AutoLog;
import com.github.houbb.auto.log.annotation.TraceId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * <p>
 * 首页 前端控制器
 * </p>
 *
 * @author binbin.hou
 * @since 2020-09-18
 */
@Controller
@TraceId
@AutoLog
public class MyIndexController {

    @RequestMapping(value = "/index")
    public String index() throws IOException {
        return "index";
    }

    @RequestMapping(value = "/")
    public String index2() throws IOException {
        return "index";
    }

}

