package com.github.houbb.sensitive.word.admin.util.exception;

import com.github.houbb.heaven.response.respcode.RespCode;

public class SensitiveWordBizException extends RuntimeException {

    private final RespCode respCode;

    public SensitiveWordBizException(RespCode respCode) {
        this.respCode = respCode;
    }

    public SensitiveWordBizException(String message, RespCode respCode) {
        super(message);
        this.respCode = respCode;
    }

    public SensitiveWordBizException(String message, Throwable cause, RespCode respCode) {
        super(message, cause);
        this.respCode = respCode;
    }

    public SensitiveWordBizException(Throwable cause, RespCode respCode) {
        super(cause);
        this.respCode = respCode;
    }

    public SensitiveWordBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, RespCode respCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.respCode = respCode;
    }
    
}
