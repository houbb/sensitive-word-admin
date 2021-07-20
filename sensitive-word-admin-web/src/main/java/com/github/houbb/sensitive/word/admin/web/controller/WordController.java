package com.github.houbb.sensitive.word.admin.web.controller;

import com.github.houbb.auto.log.annotation.AutoLog;
import com.github.houbb.auto.log.annotation.TraceId;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.iexcel.util.ExcelHelper;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.web.common.dto.resp.BaseResp;
import com.github.houbb.web.common.dto.resp.BasePageInfo;
import com.github.houbb.web.common.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.houbb.sensitive.word.admin.service.service.WordService;
import com.github.houbb.sensitive.word.admin.dal.entity.Word;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordPagePo;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * <p>
 * 敏感词表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2021-07-07
 */
@Controller
@RequestMapping("/word")
@TraceId
@AutoLog
public class WordController {

    @Autowired
    private WordService wordService;

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    /**
    * 首页
    */
    @RequestMapping("/index")
    public String index() {
        return "word/index";
    }

    /**
    * 添加元素
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/add")
    @ResponseBody
    public BaseResp add(@RequestBody final Word entity) {
        wordService.insert(entity);

        refreshSensitiveWord();
        return RespUtil.success();
    }

    /**
    * 编辑
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResp edit(final Word entity) {
        wordService.updateById(entity);

        refreshSensitiveWord();
        return RespUtil.success();
    }

    /**
    * 删除
    * @param id 实体
    * @return 结果
    */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public BaseResp remove(@PathVariable final Integer id) {
        wordService.deleteById(id);

        refreshSensitiveWord();
        return RespUtil.success();
    }

    /**
    * 列表
    * @param pageReq 入参
    * @return 结果
    */
    @RequestMapping("/list")
    @ResponseBody
    public BaseResp list(@RequestBody WordPagePo pageReq) {
        BasePageInfo<Word> pageInfo = wordService.pageQueryList(pageReq);
        return RespUtil.of(pageInfo);
    }

    /**
    * 导出
    * @param pageReq 入参
    * @param response 响应
    */
    @RequestMapping("/export")
    @ResponseBody
    @CrossOrigin
    public void export(@RequestBody WordPagePo pageReq, HttpServletResponse response) {
        final String fileName = "文件导出-敏感词表-" + System.currentTimeMillis() + ".xls";
        File file = new File(fileName);
        try {
            pageReq.setPageNum(1);
            pageReq.setPageSize(Integer.MAX_VALUE);

            BasePageInfo<Word> pageInfo = wordService.pageQueryList(pageReq);

            // 直接写入到文件
            ExcelHelper.write(file.getAbsolutePath(), pageInfo.getList());

            // 根据客户端，选择信息
            response.addHeader("content-Type", "application/octet-stream");
            response.addHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            try(InputStream in = new FileInputStream(file);
                ServletOutputStream out = response.getOutputStream();) {
                byte[] bs = new byte[1024];
                int len = -1;
                while ((len = in.read(bs)) != -1) {
                    out.write(bs, 0, len);
                }
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.deleteFile(file);
        }
    }

    /**
     * 刷新敏感詞
     *
     * 可以优化为异步，甚至批量。
     * @since 1.1.0
     */
    private void refreshSensitiveWord() {
        sensitiveWordBs.init();
    }

}
