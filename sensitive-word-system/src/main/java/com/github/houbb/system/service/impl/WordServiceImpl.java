package com.github.houbb.system.service.impl;

import java.util.List;
import com.github.houbb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.houbb.system.mapper.WordMapper;
import com.github.houbb.system.domain.Word;
import com.github.houbb.system.service.IWordService;

/**
 * 敏感词Service业务层处理
 * 
 * @author lm
 * @date 2025-05-10
 */
@Service
public class WordServiceImpl implements IWordService 
{
    @Autowired
    private WordMapper wordMapper;

    /**
     * 查询敏感词
     * 
     * @param id 敏感词主键
     * @return 敏感词
     */
    @Override
    public Word selectWordById(Long id)
    {
        return wordMapper.selectWordById(id);
    }

    /**
     * 查询敏感词列表
     * 
     * @param word 敏感词
     * @return 敏感词
     */
    @Override
    public List<Word> selectWordList(Word word)
    {
        return wordMapper.selectWordList(word);
    }

    /**
     * 新增敏感词
     * 
     * @param word 敏感词
     * @return 结果
     */
    @Override
    public int insertWord(Word word)
    {
        word.setCreateTime(DateUtils.getNowDate());
        return wordMapper.insertWord(word);
    }

    /**
     * 修改敏感词
     * 
     * @param word 敏感词
     * @return 结果
     */
    @Override
    public int updateWord(Word word)
    {
        word.setUpdateTime(DateUtils.getNowDate());
        return wordMapper.updateWord(word);
    }

    /**
     * 批量删除敏感词
     * 
     * @param ids 需要删除的敏感词主键
     * @return 结果
     */
    @Override
    public int deleteWordByIds(Long[] ids)
    {
        return wordMapper.deleteWordByIds(ids);
    }

    /**
     * 删除敏感词信息
     * 
     * @param id 敏感词主键
     * @return 结果
     */
    @Override
    public int deleteWordById(Long id)
    {
        return wordMapper.deleteWordById(id);
    }
}
