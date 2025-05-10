package com.github.houbb.system.service.impl;

import java.util.List;
import com.github.houbb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.houbb.system.mapper.TagMapper;
import com.github.houbb.system.domain.Tag;
import com.github.houbb.system.service.ITagService;

/**
 * 标签Service业务层处理
 * 
 * @author lm
 * @date 2025-05-10
 */
@Service
public class TagServiceImpl implements ITagService 
{
    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public Tag selectTagById(Long id)
    {
        return tagMapper.selectTagById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param tag 标签
     * @return 标签
     */
    @Override
    public List<Tag> selectTagList(Tag tag)
    {
        return tagMapper.selectTagList(tag);
    }

    /**
     * 新增标签
     * 
     * @param tag 标签
     * @return 结果
     */
    @Override
    public int insertTag(Tag tag)
    {
        tag.setCreateTime(DateUtils.getNowDate());
        return tagMapper.insertTag(tag);
    }

    /**
     * 修改标签
     * 
     * @param tag 标签
     * @return 结果
     */
    @Override
    public int updateTag(Tag tag)
    {
        tag.setUpdateTime(DateUtils.getNowDate());
        return tagMapper.updateTag(tag);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteTagByIds(Long[] ids)
    {
        return tagMapper.deleteTagByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteTagById(Long id)
    {
        return tagMapper.deleteTagById(id);
    }
}
