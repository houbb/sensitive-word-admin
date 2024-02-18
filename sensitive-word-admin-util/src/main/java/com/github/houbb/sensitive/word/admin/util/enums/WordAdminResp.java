package com.github.houbb.sensitive.word.admin.util.enums;

import com.github.houbb.heaven.response.respcode.RespCode;

/**
 * 单词响应
 *
 * @since 1.3.0
 */
public enum WordAdminResp implements RespCode {
    WORD_NOT_FOUND("000001", "单词信息不存在"),
    ;

    private final String code;
    private final String msg;

    WordAdminResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
