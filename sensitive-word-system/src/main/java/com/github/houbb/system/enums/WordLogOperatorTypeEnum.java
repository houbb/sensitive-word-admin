package com.github.houbb.system.enums;

/**
* 枚举值
* @author dh
*/
public enum WordLogOperatorTypeEnum {
    /**
    * 枚举值
    */
    CREATE("CREATE", "新增"),
    DELETE("DELETE", "删除"),
    UPDATE("UPDATE", "更新"),
    ;

    /**
    * 编码
    */
    private final String code;

    /**
    * 描述
    */
    private final String desc;

    WordLogOperatorTypeEnum(String code, String desc) {
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
