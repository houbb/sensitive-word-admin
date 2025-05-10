package com.github.houbb.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.github.houbb.common.annotation.Excel;
import com.github.houbb.common.core.domain.BaseEntity;

/**
 * 标签对象 tag
 * 
 * @author lm
 * @date 2025-05-10
 */
public class Tag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用自增主键 */
    private Long id;

    /** 标签编码 */
    @Excel(name = "标签编码")
    private String tagCode;

    /** 标签描述 */
    @Excel(name = "标签描述")
    private String tagLabel;

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

    public void setTagCode(String tagCode) 
    {
        this.tagCode = tagCode;
    }

    public String getTagCode() 
    {
        return tagCode;
    }

    public void setTagLabel(String tagLabel) 
    {
        this.tagLabel = tagLabel;
    }

    public String getTagLabel() 
    {
        return tagLabel;
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
            .append("tagCode", getTagCode())
            .append("tagLabel", getTagLabel())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
