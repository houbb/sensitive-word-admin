package com.github.houbb.sensitive.word.admin.service.service;

import com.github.houbb.sensitive.word.admin.dal.entity.LcEnumMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.LcEnumMappingPagePo;
import com.baomidou.mybatisplus.service.IService;
import com.github.houbb.web.common.dto.resp.BasePageInfo;

import java.util.List;

/**
 * <p>
 * 枚举映射表 服务类
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
public interface LcEnumMappingService extends IService<LcEnumMapping> {

    /**
    * 分页查询
    * @param pageReq 请求
    * @return 结果
    */
    BasePageInfo<LcEnumMapping> pageQueryList(LcEnumMappingPagePo pageReq);

    /**
    * 批量删除
    * @param ids id 列表
    */
    void deleteBatch(final List<Integer> ids);

}
