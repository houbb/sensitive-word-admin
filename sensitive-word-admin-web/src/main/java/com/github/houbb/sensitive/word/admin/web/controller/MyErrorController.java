package com.github.houbb.sensitive.word.admin.web.controller;


import com.github.houbb.auto.log.annotation.AutoLog;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 自定义的异常处理类
 * </p>
 *
 * @author binbin.hou
 * @since 2020-09-18
 */
@Controller
@AutoLog
public class MyErrorController {

    private final ErrorAttributes errorAttributes;

    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> body = this.errorAttributes.getErrorAttributes(requestAttributes, true);

        // 错误日志
        Integer statusCode = (Integer) body.get("status");
        if(statusCode.equals(404)) {
            return "error/404";
        }

        // 这里可以针对不同的错误做跳转。
        return "error/500";
    }

}

