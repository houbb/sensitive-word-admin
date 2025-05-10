package com.github.houbb.system.service.impl;

import java.util.List;
import com.github.houbb.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.houbb.system.mapper.WordLogMapper;
import com.github.houbb.system.domain.WordLog;
import com.github.houbb.system.service.IWordLogService;

/**
 * 操作日志Service业务层处理
 * 
 * @author lm
 * @date 2025-05-10
 */
@Service
public class WordLogServiceImpl implements IWordLogService 
{
    @Autowired
    private WordLogMapper wordLogMapper;

    /**
     * 查询操作日志
     * 
     * @param id 操作日志主键
     * @return 操作日志
     */
    @Override
    public WordLog selectWordLogById(Long id)
    {
        return wordLogMapper.selectWordLogById(id);
    }

    /**
     * 查询操作日志列表
     * 
     * @param wordLog 操作日志
     * @return 操作日志
     */
    @Override
    public List<WordLog> selectWordLogList(WordLog wordLog)
    {
        return wordLogMapper.selectWordLogList(wordLog);
    }

    /**
     * 新增操作日志
     * 
     * @param wordLog 操作日志
     * @return 结果
     */
    @Override
    public int insertWordLog(WordLog wordLog)
    {
        wordLog.setCreateTime(DateUtils.getNowDate());
        return wordLogMapper.insertWordLog(wordLog);
    }

    /**
     * 修改操作日志
     * 
     * @param wordLog 操作日志
     * @return 结果
     */
    @Override
    public int updateWordLog(WordLog wordLog)
    {
        wordLog.setUpdateTime(DateUtils.getNowDate());
        return wordLogMapper.updateWordLog(wordLog);
    }

    /**
     * 批量删除操作日志
     * 
     * @param ids 需要删除的操作日志主键
     * @return 结果
     */
    @Override
    public int deleteWordLogByIds(Long[] ids)
    {
        return wordLogMapper.deleteWordLogByIds(ids);
    }

    /**
     * 删除操作日志信息
     * 
     * @param id 操作日志主键
     * @return 结果
     */
    @Override
    public int deleteWordLogById(Long id)
    {
        return wordLogMapper.deleteWordLogById(id);
    }
}
