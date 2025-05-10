package com.github.houbb.system.mapper;

import java.util.List;
import com.github.houbb.system.domain.WordTagMapping;

/**
 * 标签单词映射Mapper接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface WordTagMappingMapper 
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
     * 删除标签单词映射
     * 
     * @param id 标签单词映射主键
     * @return 结果
     */
    public int deleteWordTagMappingById(Long id);

    /**
     * 批量删除标签单词映射
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWordTagMappingByIds(Long[] ids);
}
