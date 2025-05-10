package com.github.houbb.system.service.impl;

import java.util.List;
import com.github.houbb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.houbb.system.mapper.WordTagMappingMapper;
import com.github.houbb.system.domain.WordTagMapping;
import com.github.houbb.system.service.IWordTagMappingService;

/**
 * 标签单词映射Service业务层处理
 * 
 * @author lm
 * @date 2025-05-10
 */
@Service
public class WordTagMappingServiceImpl implements IWordTagMappingService 
{
    @Autowired
    private WordTagMappingMapper wordTagMappingMapper;

    /**
     * 查询标签单词映射
     * 
     * @param id 标签单词映射主键
     * @return 标签单词映射
     */
    @Override
    public WordTagMapping selectWordTagMappingById(Long id)
    {
        return wordTagMappingMapper.selectWordTagMappingById(id);
    }

    /**
     * 查询标签单词映射列表
     * 
     * @param wordTagMapping 标签单词映射
     * @return 标签单词映射
     */
    @Override
    public List<WordTagMapping> selectWordTagMappingList(WordTagMapping wordTagMapping)
    {
        return wordTagMappingMapper.selectWordTagMappingList(wordTagMapping);
    }

    /**
     * 新增标签单词映射
     * 
     * @param wordTagMapping 标签单词映射
     * @return 结果
     */
    @Override
    public int insertWordTagMapping(WordTagMapping wordTagMapping)
    {
        wordTagMapping.setCreateTime(DateUtils.getNowDate());
        return wordTagMappingMapper.insertWordTagMapping(wordTagMapping);
    }

    /**
     * 修改标签单词映射
     * 
     * @param wordTagMapping 标签单词映射
     * @return 结果
     */
    @Override
    public int updateWordTagMapping(WordTagMapping wordTagMapping)
    {
        wordTagMapping.setUpdateTime(DateUtils.getNowDate());
        return wordTagMappingMapper.updateWordTagMapping(wordTagMapping);
    }

    /**
     * 批量删除标签单词映射
     * 
     * @param ids 需要删除的标签单词映射主键
     * @return 结果
     */
    @Override
    public int deleteWordTagMappingByIds(Long[] ids)
    {
        return wordTagMappingMapper.deleteWordTagMappingByIds(ids);
    }

    /**
     * 删除标签单词映射信息
     * 
     * @param id 标签单词映射主键
     * @return 结果
     */
    @Override
    public int deleteWordTagMappingById(Long id)
    {
        return wordTagMappingMapper.deleteWordTagMappingById(id);
    }
}
