package com.github.houbb.sensitive.word.admin.service.service.impl;

import com.github.houbb.sensitive.word.admin.dal.entity.LcEnumMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.LcEnumMappingPagePo;
import com.github.houbb.sensitive.word.admin.dal.mapper.LcEnumMappingMapper;
import com.github.houbb.sensitive.word.admin.service.service.LcEnumMappingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.houbb.web.common.dto.resp.BasePageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Collections;

/**
 * <p>
 * 枚举映射表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
@Service
public class LcEnumMappingServiceImpl extends ServiceImpl<LcEnumMappingMapper, LcEnumMapping> implements LcEnumMappingService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Integer> ids) {
        for(Integer id : ids) {
            this.deleteById(id);
        }
    }

    /**
    * 分页查询
    *
    * @param pageReq 请求
    * @return 结果
    */
    @Override
    public BasePageInfo<LcEnumMapping> pageQueryList(LcEnumMappingPagePo pageReq) {
        Wrapper<LcEnumMapping> wrapper = buildPageQueryWrapper(pageReq);

        Page<LcEnumMapping> page = new Page<>(pageReq.getPageNum(), pageReq.getPageSize());
        page = this.selectPage(page, wrapper);
        BasePageInfo<LcEnumMapping> pageInfo = new BasePageInfo<>();
        pageInfo.setList(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setTotalPages(page.getPages());
        return pageInfo;
    }

    /**
    * 构建分页查询的 wrapper
    *
    * @param pageReq 入参对象
    * @return 结果
    */
    private Wrapper<LcEnumMapping> buildPageQueryWrapper(LcEnumMappingPagePo pageReq) {
        Wrapper<LcEnumMapping> wrapper = new EntityWrapper<>();
        List<Field> fieldList = ClassUtil.getAllFieldList(pageReq.getClass());
        for(Field field : fieldList) {
            String fieldName = field.getName();
            if("pageSize".equals(fieldName)
                || "pageNum".equals(fieldName)) {
                continue;
            }

            // 处理非空字段
            Object fieldValue = ReflectFieldUtil.getValue(field, pageReq);
            if(null == fieldValue) {
                continue;
            }
            // 字符串空过滤
            if(String.class.equals(field.getType())) {
                String text = (String) fieldValue;
                    if(StringUtil.isBlank(text)) {
                    continue;
                }
            }
            String columnName = StringUtil.camelToUnderline(fieldName);
            wrapper.eq(columnName, fieldValue);
        }

        // 更新时间倒排
        wrapper.orderDesc(Collections.singleton("update_time"));
        return wrapper;
    }

}
