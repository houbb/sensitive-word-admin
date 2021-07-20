package com.github.houbb.sensitive.word.admin.service.support.sensitive;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.service.service.WordService;
import com.github.houbb.sensitive.word.admin.util.enums.WordStatusEnum;
import com.github.houbb.sensitive.word.admin.util.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
@Component
public class MyDdWordDeny implements IWordDeny {

    @Autowired
    private WordService wordService;

    @Override
    public List<String> deny() {
        Wrapper<Word> wordWrapper = new EntityWrapper<>();
        wordWrapper.eq("type", WordTypeEnum.DENY.getCode());
        wordWrapper.eq("status", WordStatusEnum.S.getCode());

        List<Word> wordList = wordService.selectList(wordWrapper);

        return CollectionUtil.toList(wordList, new IHandler<Word, String>() {
            @Override
            public String handle(Word word) {
                return word.getWord();
            }
        });
    }

}
