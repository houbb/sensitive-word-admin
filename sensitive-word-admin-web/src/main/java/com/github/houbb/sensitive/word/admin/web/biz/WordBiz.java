package com.github.houbb.sensitive.word.admin.web.biz;

import com.github.houbb.heaven.util.lang.BeanUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.id.core.util.IdHelper;
import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;
import com.github.houbb.sensitive.word.admin.service.service.WordLogService;
import com.github.houbb.sensitive.word.admin.service.service.WordService;
import com.github.houbb.sensitive.word.admin.util.enums.WordAdminResp;
import com.github.houbb.sensitive.word.admin.util.enums.WordLogOperatorTypeEnum;
import com.github.houbb.sensitive.word.admin.util.exception.SensitiveWordBizException;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        wordLog.setCreateTime(null);
        wordLog.setUpdateTime(null);

        wordLogService.insert(wordLog);
    }


    @Transactional(rollbackFor = Exception.class)
    public void editTx(final Word entity) {
        //1. 查出历史信息
        Word oldEntity = wordService.selectById(entity.getId());
        if(oldEntity == null) {
            throw new SensitiveWordBizException(WordAdminResp.WORD_NOT_FOUND);
        }

        wordService.updateById(entity);

        WordLog wordLog = new WordLog();
        BeanUtil.copyProperties(entity, wordLog);
        wordLog.setBatchId(IdHelper.uuid32());
        wordLog.setId(null);
        wordLog.setOperatorType(WordLogOperatorTypeEnum.UPDATE.getCode());
        wordLog.setWordBefore(null);
        wordLog.setWordAfter(entity.getWord());
        wordLog.setCreateTime(null);
        wordLog.setUpdateTime(null);

        wordLogService.insert(wordLog);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeTx(final Integer id) {
        //1. 查出历史信息
        Word oldEntity = wordService.selectById(id);
        if(oldEntity == null) {
            //TODO: 细化，响应枚举
            throw new SensitiveWordBizException(WordAdminResp.WORD_NOT_FOUND);
        }
        wordService.deleteById(id);

        WordLog wordLog = new WordLog();
        BeanUtil.copyProperties(oldEntity, wordLog);
        wordLog.setBatchId(IdHelper.uuid32());
        wordLog.setId(null);
        wordLog.setOperatorType(WordLogOperatorTypeEnum.DELETE.getCode());
        wordLog.setWordBefore(oldEntity.getWord());
        wordLog.setWordAfter(null);
        wordLog.setCreateTime(null);
        wordLog.setUpdateTime(null);

        wordLogService.insert(wordLog);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeBatchTx(final List<Integer> ids) {
        if(CollectionUtil.isEmpty(ids)) {
            throw new SensitiveWordBizException(WordAdminResp.WORD_NOT_FOUND);
        }
        //1. 查出历史信息
        List<Word> oldEntityList = wordService.selectBatchIds(ids);
        if(CollectionUtil.isEmpty(oldEntityList)) {
            throw new SensitiveWordBizException(WordAdminResp.WORD_NOT_FOUND);
        }
        wordService.deleteBatch(ids);

        //2. 构建日志
        List<WordLog> wordLogList = new ArrayList<>();
        final String batchId = IdHelper.uuid32();
        for(Word word : oldEntityList) {
            WordLog wordLog = new WordLog();
            BeanUtil.copyProperties(word, wordLog);
            wordLog.setBatchId(batchId);
            wordLog.setId(null);
            wordLog.setOperatorType(WordLogOperatorTypeEnum.DELETE.getCode());
            wordLog.setWordBefore(word.getWord());
            wordLog.setWordAfter(null);
            wordLog.setCreateTime(null);
            wordLog.setUpdateTime(null);

            wordLogList.add(wordLog);
        }

        wordLogService.insertBatch(wordLogList);
    }


}
