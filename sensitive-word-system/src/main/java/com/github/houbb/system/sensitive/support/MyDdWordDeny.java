package com.github.houbb.system.sensitive.support;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.system.domain.Word;
import com.github.houbb.system.enums.WordStatusEnum;
import com.github.houbb.system.enums.WordTypeEnum;
import com.github.houbb.system.service.IWordService;
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
    private IWordService wordService;

    @Override
    public List<String> deny() {
        Word word = new com.github.houbb.system.domain.Word();
        word.setStatus(WordStatusEnum.Y.getCode());
        word.setType(WordTypeEnum.DENY.getCode());
        List<Word> wordList = wordService.selectWordList(word);

        return CollectionUtil.toList(wordList, new IHandler<Word, String>() {
            @Override
            public String handle(Word word) {
                return word.getWord();
            }
        });
    }

}
