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
 * 枚举映射表
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
public class LcEnumMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(headName = "自增主键")
    private Integer id;

    /**
     * 表名称
     */
    @TableField("table_name")
    @ExcelField(headName = "表名称")
    private String tableName;

    /**
     * 字段名称
     */
    @TableField("column_name")
    @ExcelField(headName = "字段名称")
    private String columnName;

    /**
     * 字段编码
     */
    @TableField("key")
    @ExcelField(headName = "字段编码")
    private String key;

    /**
     * 字段显示
     */
    @TableField("label")
    @ExcelField(headName = "字段显示")
    private String label;

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
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        return "LcEnumMapping{" +
        "id=" + id +
        ", tableName=" + tableName +
        ", columnName=" + columnName +
        ", key=" + key +
        ", label=" + label +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
