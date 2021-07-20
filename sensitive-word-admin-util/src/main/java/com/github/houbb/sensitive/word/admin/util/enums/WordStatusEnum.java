package com.github.houbb.sensitive.word.admin.util.enums;

/**
* 枚举值
* @author Administrator
*/
public enum WordStatusEnum {
    /**
    * 枚举值
    */
    F("F", "失效"),
    S("S", "正常"),
    ;

    /**
    * 编码
    */
    private final String code;

    /**
    * 描述
    */
    private final String desc;

    WordStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
