package com.github.houbb.sensitive.word.admin.util.enums;

import com.github.houbb.low.code.gen.bs.LowCodeEnumGenBs;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class EnumTest {

    public static void main(String[] args) {
        LowCodeEnumGenBs.newInstance()
                .baseDir("D:\\gitee\\sensitive-word-admin\\sensitive-word-admin-util\\src\\main\\java\\com\\github\\houbb\\sensitive\\word\\admin\\util\\enums\\")
                .packagePath("com.github.houbb.sensitive.word.admin.util.enums")
                .url("jdbc:mysql://localhost:3306/sensitive_word?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC")
                .execute();
    }

}
