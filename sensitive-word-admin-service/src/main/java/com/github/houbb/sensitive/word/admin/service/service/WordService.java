package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

/**
 * <p>
 * 敏感词表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
public interface WordService extends IService<Word> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<Word> pageQueryList(WordPagePo pageReq);

}
