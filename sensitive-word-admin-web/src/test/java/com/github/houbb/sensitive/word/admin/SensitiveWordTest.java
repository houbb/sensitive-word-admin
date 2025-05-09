package com.github.houbb.sensitive.word.admin;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Assert;

import java.util.List;

public class SensitiveWordTest {

    public static void main(String[] args) {
        final String text = "我们的用户信息";

        List<String> wordList = SensitiveWordHelper.findAll(text);
        Assert.assertTrue(wordList.size() == 0);
    }

}
