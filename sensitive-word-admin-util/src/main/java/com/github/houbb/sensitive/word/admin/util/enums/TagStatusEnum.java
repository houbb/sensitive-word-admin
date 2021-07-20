package com.github.houbb.sensitive.word.admin.util.enums;

/**
* ö��ֵ
* @author binbin.hou
*/
public enum TagStatusEnum {
    /**
    * ö��ֵ
    */
    F("F", "ʧЧ"),
    S("S", "����"),
    ;

    /**
    * ����
    */
    private final String code;

    /**
    * ����
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
