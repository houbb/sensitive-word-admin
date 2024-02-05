package com.github.houbb.sensitive.word.admin.dal.entity.po;

import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;

/**
 * <p>
 * 敏感词操作日志表-分页查询对象
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
public class WordLogPagePo extends WordLog {

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
        return "WordLogPagePo{" +
        "pageSize=" + pageSize +
        ", pageNum=" + pageNum +
        "} " + super.toString();
    }

}