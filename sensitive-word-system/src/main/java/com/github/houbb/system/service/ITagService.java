package com.github.houbb.system.service;

import java.util.List;
import com.github.houbb.system.domain.Tag;

/**
 * 标签Service接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface ITagService 
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public Tag selectTagById(Long id);

    /**
     * 查询标签列表
     * 
     * @param tag 标签
     * @return 标签集合
     */
    public List<Tag> selectTagList(Tag tag);

    /**
     * 新增标签
     * 
     * @param tag 标签
     * @return 结果
     */
    public int insertTag(Tag tag);

    /**
     * 修改标签
     * 
     * @param tag 标签
     * @return 结果
     */
    public int updateTag(Tag tag);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键集合
     * @return 结果
     */
    public int deleteTagByIds(Long[] ids);

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteTagById(Long id);
}
