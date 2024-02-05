package com.github.houbb.sensitive.word.admin.web.biz;

import com.github.houbb.heaven.util.lang.BeanUtil;
import com.github.houbb.id.core.util.IdHelper;
import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;
import com.github.houbb.sensitive.word.admin.service.service.WordLogService;
import com.github.houbb.sensitive.word.admin.service.service.WordService;
import com.github.houbb.sensitive.word.admin.util.enums.WordLogOperatorTypeEnum;
import com.github.houbb.sensitive.word.admin.util.exception.SensitiveWordBizException;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since v1.3.0
 */
@Component
@Slf4j
public class WordBiz {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordLogService wordLogService;

    @Transactional(rollbackFor = Exception.class)
    public void addTx(final Word entity) {
        wordService.insert(entity);

        WordLog wordLog = new WordLog();
        BeanUtil.copyProperties(entity, wordLog);
        wordLog.setBatchId(IdHelper.uuid32());
        wordLog.setId(null);
        wordLog.setOperatorType(WordLogOperatorTypeEnum.CREATE.getCode());
        wordLog.setWordBefore(null);
        wordLog.setWordAfter(entity.getWord());

        wordLogService.insert(wordLog);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editTx(final Word entity) {
        //1. 查出历史信息
        Word oldEntity = wordService.selectById(entity.getId());
        if(oldEntity == null) {
            //TODO: 细化，响应枚举
            throw new SensitiveWordBizException();
        }

        wordService.updateById(entity);

        WordLog wordLog = new WordLog();
        BeanUtil.copyProperties(entity, wordLog);
        wordLog.setBatchId(IdHelper.uuid32());
        wordLog.setId(null);
        wordLog.setOperatorType(WordLogOperatorTypeEnum.CREATE.getCode());
        wordLog.setWordBefore(null);
        wordLog.setWordAfter(entity.getWord());

        wordLogService.insert(wordLog);
    }


}
