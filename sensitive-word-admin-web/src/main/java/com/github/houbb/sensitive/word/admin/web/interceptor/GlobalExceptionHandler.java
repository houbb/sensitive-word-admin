package com.github.houbb.sensitive.word.admin.web.interceptor;

import com.github.houbb.web.common.dto.resp.BaseResp;
import com.github.houbb.web.common.util.RespUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author binbin.hou
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 日志
     * @since 0.0.3
     */
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResp globalException(HttpServletResponse response, Exception ex){
        logger.error("全局异常", ex);
        // 异常输出
        return RespUtil.fail(ex.getMessage());
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param ex 异常
     * @return 结果
     * @since 0.0.8
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseEntity<Object> handleBindException(BindException ex) {
        FieldError err = ex.getFieldError();
        String message = "参数{".concat(err.getField()).concat("}").concat(err.getDefaultMessage());
        return ResponseEntity.ok(RespUtil.fail(message));
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param ex 异常
     * @return 结果
     * @since 0.0.8
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError err = ex.getBindingResult().getFieldError();
        String message = "参数{".concat(err.getField()).concat("}").concat(err.getDefaultMessage());
        return ResponseEntity.ok(RespUtil.fail(message));
    }

}
