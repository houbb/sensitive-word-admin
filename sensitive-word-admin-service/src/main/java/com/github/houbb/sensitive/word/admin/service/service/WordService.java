package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

import java.util.List;

/**
 * <p>
 * 敏感词表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
public interface WordService extends IService<Word> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<Word> pageQueryList(WordPagePo pageReq);

    /**
    * 批量删除
    * @param ids id 列表
    */
    void deleteBatch(final List<Integer> ids);

}
