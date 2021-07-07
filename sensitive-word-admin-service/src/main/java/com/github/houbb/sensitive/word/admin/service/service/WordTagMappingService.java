package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.WordTagMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordTagMappingPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

/**
 * <p>
 * 标签单词映射表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
public interface WordTagMappingService extends IService<WordTagMapping> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<WordTagMapping> pageQueryList(WordTagMappingPagePo pageReq);

}
