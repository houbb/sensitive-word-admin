package com.github.houbb.sensitive.word.admin.service.service.impl;

import com.github.houbb.sensitive.word.admin.dal.entity.WordTagMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordTagMappingPagePo;
import com.github.houbb.sensitive.word.admin.dal.mapper.WordTagMappingMapper;
import com.github.houbb.sensitive.word.admin.service.service.WordTagMappingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.github.houbb.web.common.dto.resp.BasePageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ClassUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * 标签单词映射表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
@Service
public class WordTagMappingServiceImpl extends ServiceImpl<WordTagMappingMapper, WordTagMapping> implements WordTagMappingService {

    /**
    * 分页查询
    *
    * @param pageReq 请求
    * @return 结果
    */
    @Override
    public BasePageInfo<WordTagMapping> pageQueryList(WordTagMappingPagePo pageReq) {
        Wrapper<WordTagMapping> wrapper = buildPageQueryWrapper(pageReq);

        Page<WordTagMapping> page = new Page<>(pageReq.getPageNum(), pageReq.getPageSize());
        page = this.selectPage(page, wrapper);
        BasePageInfo<WordTagMapping> pageInfo = new BasePageInfo<>();
        pageInfo.setList(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    /**
    * 构建分页查询的 wrapper
    *
    * @param pageReq 入参对象
    * @return 结果
    */
    private Wrapper<WordTagMapping> buildPageQueryWrapper(WordTagMappingPagePo pageReq) {
        Wrapper<WordTagMapping> wrapper = new EntityWrapper<>();
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

        return wrapper;
    }

}
