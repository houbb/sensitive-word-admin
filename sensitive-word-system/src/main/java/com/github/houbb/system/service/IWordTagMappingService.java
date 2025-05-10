package com.github.houbb.system.service;

import java.util.List;
import com.github.houbb.system.domain.WordTagMapping;

/**
 * 标签单词映射Service接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface IWordTagMappingService 
{
    /**
     * 查询标签单词映射
     * 
     * @param id 标签单词映射主键
     * @return 标签单词映射
     */
    public WordTagMapping selectWordTagMappingById(Long id);

    /**
     * 查询标签单词映射列表
     * 
     * @param wordTagMapping 标签单词映射
     * @return 标签单词映射集合
     */
    public List<WordTagMapping> selectWordTagMappingList(WordTagMapping wordTagMapping);

    /**
     * 新增标签单词映射
     * 
     * @param wordTagMapping 标签单词映射
     * @return 结果
     */
    public int insertWordTagMapping(WordTagMapping wordTagMapping);

    /**
     * 修改标签单词映射
     * 
     * @param wordTagMapping 标签单词映射
     * @return 结果
     */
    public int updateWordTagMapping(WordTagMapping wordTagMapping);

    /**
     * 批量删除标签单词映射
     * 
     * @param ids 需要删除的标签单词映射主键集合
     * @return 结果
     */
    public int deleteWordTagMappingByIds(Long[] ids);

    /**
     * 删除标签单词映射信息
     * 
     * @param id 标签单词映射主键
     * @return 结果
     */
    public int deleteWordTagMappingById(Long id);
}
