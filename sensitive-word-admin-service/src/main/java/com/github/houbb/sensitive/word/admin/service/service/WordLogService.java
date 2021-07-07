package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordLogPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

/**
 * <p>
 * 敏感词操作日志表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
public interface WordLogService extends IService<WordLog> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<WordLog> pageQueryList(WordLogPagePo pageReq);

}
