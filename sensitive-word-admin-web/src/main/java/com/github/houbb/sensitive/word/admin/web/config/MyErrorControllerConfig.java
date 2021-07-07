package com.github.houbb.sensitive.word.admin.web.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Component;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Component
public class MyErrorControllerConfig implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
