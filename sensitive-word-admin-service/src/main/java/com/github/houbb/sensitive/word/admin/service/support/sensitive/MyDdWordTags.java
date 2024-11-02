package com.github.houbb.sensitive.word.admin.service.support.sensitive;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.admin.dal.entity.Tag;
import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.WordTagMapping;
import com.github.houbb.sensitive.word.admin.service.service.TagService;
import com.github.houbb.sensitive.word.admin.service.service.WordService;
import com.github.houbb.sensitive.word.admin.service.service.WordTagMappingService;
import com.github.houbb.sensitive.word.admin.util.enums.TagStatusEnum;
import com.github.houbb.sensitive.word.admin.util.enums.WordStatusEnum;
import com.github.houbb.sensitive.word.admin.util.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
@Component
public class MyDdWordTags implements IWordTag {

    @Autowired
    private WordTagMappingService wordTagMappingService;

    @Autowired
    private TagService tagService;

    @Override
    public Set<String> getTag(String word) {
        if (StringUtil.isEmpty(word)) {
            return Collections.emptySet();
        }

        // 获取单词对应的 TAG codes
        Wrapper<WordTagMapping> wordTagMappingWrapper = new EntityWrapper<>();
        wordTagMappingWrapper.eq("word", word);
        wordTagMappingWrapper.setSqlSelect("tag_code");
        List<WordTagMapping> mappingList = wordTagMappingService.selectList(wordTagMappingWrapper);
        if (CollectionUtil.isEmpty(mappingList)) {
            return Collections.emptySet();
        }

        // 根据 tag code 获取对应的 TAG LABEL
        List<String> wordTagCodeList = mappingList.stream().map(WordTagMapping::getTagCode).collect(Collectors.toList());
        Wrapper<Tag> tagWrapper = new EntityWrapper<>();
        tagWrapper.eq("status", TagStatusEnum.S.getCode());
        tagWrapper.in("tag_code", wordTagCodeList);
        tagWrapper.setSqlSelect("tag_label");
        List<Tag> dbTagList = tagService.selectList(tagWrapper);
        if (CollectionUtil.isEmpty(dbTagList)) {
            return Collections.emptySet();
        }
        return dbTagList.stream().map(Tag::getTagLabel).collect(Collectors.toSet());
    }

}
