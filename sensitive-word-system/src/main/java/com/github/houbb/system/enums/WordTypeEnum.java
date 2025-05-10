package com.github.houbb.system.enums;

/**
* 枚举值
* @author dh
*/
public enum WordTypeEnum {
    /**
    * 枚举值
    */
    ALLOW("ALLOW", "允许"),
    DENY("DENY", "禁止"),
    ;

    /**
    * 编码
    */
    private final String code;

    /**
    * 描述
    */
    private final String desc;

    WordTypeEnum(String code, String desc) {
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
