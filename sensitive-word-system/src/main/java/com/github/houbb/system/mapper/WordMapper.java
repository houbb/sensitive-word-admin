package com.github.houbb.system.mapper;

import java.util.List;
import com.github.houbb.system.domain.Word;

/**
 * 敏感词Mapper接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface WordMapper 
{
    /**
     * 查询敏感词
     * 
     * @param id 敏感词主键
     * @return 敏感词
     */
    public Word selectWordById(Long id);

    /**
     * 查询敏感词列表
     * 
     * @param word 敏感词
     * @return 敏感词集合
     */
    public List<Word> selectWordList(Word word);

    /**
     * 新增敏感词
     * 
     * @param word 敏感词
     * @return 结果
     */
    public int insertWord(Word word);

    /**
     * 修改敏感词
     * 
     * @param word 敏感词
     * @return 结果
     */
    public int updateWord(Word word);

    /**
     * 删除敏感词
     * 
     * @param id 敏感词主键
     * @return 结果
     */
    public int deleteWordById(Long id);

    /**
     * 批量删除敏感词
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWordByIds(Long[] ids);
}
