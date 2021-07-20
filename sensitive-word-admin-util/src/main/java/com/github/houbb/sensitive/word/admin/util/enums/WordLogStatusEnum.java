package com.github.houbb.sensitive.word.admin.util.enums;

/**
* ö��ֵ
* @author binbin.hou
*/
public enum WordLogStatusEnum {
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

    WordLogStatusEnum(String code, String desc) {
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
