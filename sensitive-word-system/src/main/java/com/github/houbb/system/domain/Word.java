package com.github.houbb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.github.houbb.common.annotation.Excel;
import com.github.houbb.common.core.domain.BaseEntity;

/**
 * 敏感词对象 word
 * 
 * @author lm
 * @date 2025-05-10
 */
public class Word extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用自增主键 */
    private Long id;

    /** 单词 */
    @Excel(name = "单词")
    private String word;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("word", getWord())
            .append("type", getType())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
