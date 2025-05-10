package com.github.houbb.system.mapper.define;

import com.github.houbb.system.domain.po.WordTagPo;

import java.util.List;

/**
 * 标签单词映射Mapper接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface DefineWordTagMappingMapper
{

    /**
     * select distinct
     * mapping.word as word, tag.label as tagLabel
     * from word_tag_mapping mapping
     * INNER JOIN tag
     * ON mapping.tag_code = tag.tag_code
     * where tag.status='Y'
     *
     * @return 结果
     */
    List<WordTagPo> selectAllEnableWordTagList();

}
