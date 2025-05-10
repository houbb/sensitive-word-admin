package com.github.houbb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.github.houbb.common.annotation.Excel;
import com.github.houbb.common.core.domain.BaseEntity;

/**
 * 操作日志对象 word_log
 * 
 * @author lm
 * @date 2025-05-10
 */
public class WordLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用自增主键 */
    private Long id;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchId;

    /** 单词 */
    @Excel(name = "单词")
    private String word;

    /** 变更前单词 */
    @Excel(name = "变更前单词")
    private String wordBefore;

    /** 变更后单词 */
    @Excel(name = "变更后单词")
    private String wordAfter;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 单词状态 */
    @Excel(name = "单词状态")
    private String status;

    /** 操作类别 */
    @Excel(name = "操作类别")
    private String operatorType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setBatchId(String batchId) 
    {
        this.batchId = batchId;
    }

    public String getBatchId() 
    {
        return batchId;
    }

    public void setWord(String word) 
    {
        this.word = word;
    }

    public String getWord() 
    {
        return word;
    }

    public void setWordBefore(String wordBefore) 
    {
        this.wordBefore = wordBefore;
    }

    public String getWordBefore() 
    {
        return wordBefore;
    }

    public void setWordAfter(String wordAfter) 
    {
        this.wordAfter = wordAfter;
    }

    public String getWordAfter() 
    {
        return wordAfter;
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

    public void setOperatorType(String operatorType) 
    {
        this.operatorType = operatorType;
    }

    public String getOperatorType() 
    {
        return operatorType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("batchId", getBatchId())
            .append("word", getWord())
            .append("wordBefore", getWordBefore())
            .append("wordAfter", getWordAfter())
            .append("type", getType())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("operatorType", getOperatorType())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
