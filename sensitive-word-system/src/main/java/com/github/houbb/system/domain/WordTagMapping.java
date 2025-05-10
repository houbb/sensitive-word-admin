package com.github.houbb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.github.houbb.common.annotation.Excel;
import com.github.houbb.common.core.domain.BaseEntity;

/**
 * 标签单词映射对象 word_tag_mapping
 * 
 * @author lm
 * @date 2025-05-10
 */
public class WordTagMapping extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用自增主键 */
    private Long id;

    /** 单词信息 */
    @Excel(name = "单词信息")
    private String word;

    /** 标签编码 */
    @Excel(name = "标签编码")
    private String tagCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setWord(String word) 
    {
        this.word = word;
    }

    public String getWord() 
    {
        return word;
    }

    public void setTagCode(String tagCode) 
    {
        this.tagCode = tagCode;
    }

    public String getTagCode() 
    {
        return tagCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("word", getWord())
            .append("tagCode", getTagCode())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
