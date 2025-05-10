package com.github.houbb.system.sensitive.support;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordTag;
import com.github.houbb.system.domain.po.WordTagPo;
import com.github.houbb.system.mapper.define.DefineWordTagMappingMapper;
import com.github.houbb.system.sensitive.SensitiveWordConfigSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author binbin.hou
 * @since 1.1.0
 */
@Component
public class MyDdWordTags implements IWordTag {

    private static final Logger log = LoggerFactory.getLogger(MyDdWordTags.class);

    @Autowired
    private DefineWordTagMappingMapper defineWordTagMappingMapper;

    private Map<String, Set<String>> map = new HashMap<>();

    public void init() {
        log.info(">>>>>>>>>>> MyWordTags init start");
        List<WordTagPo> wordTagPoList = defineWordTagMappingMapper.selectAllEnableWordTagList();

        Map<String, Set<String>> tempMap = buildWordTagMap(wordTagPoList);
        this.map = tempMap;
        log.info(">>>>>>>>>>> MyWordTags init end, tempMap.size={}", tempMap.size());
    }

    private Map<String, Set<String>> buildWordTagMap(List<WordTagPo> wordTagPoList) {
        Map<String, Set<String>> map = new HashMap<>();
        if(CollectionUtil.isEmpty(wordTagPoList)) {
            return map;
        }

        for(WordTagPo po : wordTagPoList) {
            String word = po.getWord();
            Set<String> set = map.getOrDefault(word, new HashSet<>());
            set.add(po.getTagLabel());

            map.put(word, set);
        }

        return map;
    }

    @Override
    public Set<String> getTag(String word) {
        return map.get(word);
    }

}
