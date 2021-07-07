package com.github.houbb.sensitive.word.admin.dal.entity;

import com.github.houbb.iexcel.annotation.ExcelField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 标签单词映射表
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
public class WordTagMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(headName = "应用自增主键")
    private Integer id;

    /**
     * 单词信息
     */
    @TableField("word")
    @ExcelField(headName = "单词信息")
    private String word;

    /**
     * 标签编码
     */
    @TableField("tag_code")
    @ExcelField(headName = "标签编码")
    private String tagCode;

    /**
     * 操作员名称
     */
    @TableField("operator_id")
    @ExcelField(headName = "操作员名称")
    private String operatorId;

    /**
     * 创建时间戳
     */
    @TableField("create_time")
    @ExcelField(headName = "创建时间戳")
    private Date createTime;

    /**
     * 更新时间戳
     */
    @TableField("update_time")
    @ExcelField(headName = "更新时间戳")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WordTagMapping{" +
        "id=" + id +
        ", word=" + word +
        ", tagCode=" + tagCode +
        ", operatorId=" + operatorId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
