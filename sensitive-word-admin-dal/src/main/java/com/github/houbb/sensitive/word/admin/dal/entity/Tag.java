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
 * 标签表
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(headName = "应用自增主键")
    private Integer id;

    /**
     * 标签编码
     */
    @TableField("tag_code")
    @ExcelField(headName = "标签编码")
    private String tagCode;

    /**
     * 标签描述
     */
    @TableField("tag_label")
    @ExcelField(headName = "标签描述")
    private String tagLabel;

    /**
     * 状态
     */
    @TableField("status")
    @ExcelField(headName = "状态")
    private String status;

    /**
     * 配置描述
     */
    @TableField("remark")
    @ExcelField(headName = "配置描述")
    private String remark;

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
    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }
    public String getTagLabel() {
        return tagLabel;
    }

    public void setTagLabel(String tagLabel) {
        this.tagLabel = tagLabel;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Tag{" +
        "id=" + id +
        ", tagCode=" + tagCode +
        ", tagLabel=" + tagLabel +
        ", status=" + status +
        ", remark=" + remark +
        ", operatorId=" + operatorId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
