package com.github.houbb.system.enums;

/**
* 枚举值
* @author dh
*/
public enum TagStatusEnum {
    /**
    * 枚举值
    */
    Y("Y", "正常"),
    N("N", "失效"),
    ;

    /**
    * 编码
    */
    private final String code;

    /**
    * 描述
    */
    private final String desc;

    TagStatusEnum(String code, String desc) {
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
