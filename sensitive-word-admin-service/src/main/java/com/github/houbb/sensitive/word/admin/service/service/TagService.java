package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.Tag;
import com.github.houbb.sensitive.word.admin.dal.entity.po.TagPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
public interface TagService extends IService<Tag> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<Tag> pageQueryList(TagPagePo pageReq);

    /**
    * 批量删除
    * @param ids id 列表
    */
    void deleteBatch(final List<Integer> ids);

}
