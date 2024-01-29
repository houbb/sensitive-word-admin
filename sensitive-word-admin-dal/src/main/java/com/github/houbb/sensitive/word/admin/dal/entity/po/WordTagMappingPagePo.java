package com.github.houbb.sensitive.word.admin.dal.entity.po;

import com.github.houbb.sensitive.word.admin.dal.entity.WordTagMapping;

/**
 * <p>
 * 标签单词映射表-分页查询对象
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
public class WordTagMappingPagePo extends WordTagMapping {

    /**
    * 分页大小
    */
    private Integer pageSize;

    /**
    * 当前页码
    */
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "WordTagMappingPagePo{" +
        "pageSize=" + pageSize +
        ", pageNum=" + pageNum +
        "} " + super.toString();
    }

}