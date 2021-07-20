package com.github.houbb.sensitive.word.admin.util.enums;

/**
* ö��ֵ
* @author binbin.hou
*/
public enum WordLogTypeEnum {
    /**
    * ö��ֵ
    */
    ALLOW("ALLOW", "����"),
    DENY("DENY", "��ֹ"),
    ;

    /**
    * ����
    */
    private final String code;

    /**
    * ����
    */
    private final String desc;

    WordLogTypeEnum(String code, String desc) {
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
