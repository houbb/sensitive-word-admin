package com.github.houbb.system.service;

import java.util.List;
import com.github.houbb.system.domain.WordLog;

/**
 * 操作日志Service接口
 * 
 * @author lm
 * @date 2025-05-10
 */
public interface IWordLogService 
{
    /**
     * 查询操作日志
     * 
     * @param id 操作日志主键
     * @return 操作日志
     */
    public WordLog selectWordLogById(Long id);

    /**
     * 查询操作日志列表
     * 
     * @param wordLog 操作日志
     * @return 操作日志集合
     */
    public List<WordLog> selectWordLogList(WordLog wordLog);

    /**
     * 新增操作日志
     * 
     * @param wordLog 操作日志
     * @return 结果
     */
    public int insertWordLog(WordLog wordLog);

    /**
     * 修改操作日志
     * 
     * @param wordLog 操作日志
     * @return 结果
     */
    public int updateWordLog(WordLog wordLog);

    /**
     * 批量删除操作日志
     * 
     * @param ids 需要删除的操作日志主键集合
     * @return 结果
     */
    public int deleteWordLogByIds(Long[] ids);

    /**
     * 删除操作日志信息
     * 
     * @param id 操作日志主键
     * @return 结果
     */
    public int deleteWordLogById(Long id);
}
