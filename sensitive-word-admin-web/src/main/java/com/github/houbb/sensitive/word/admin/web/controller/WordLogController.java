package com.github.houbb.sensitive.word.admin.web.controller;

import com.github.houbb.menu.api.annotation.Menu;
import com.github.houbb.auto.log.annotation.AutoLog;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.iexcel.util.ExcelHelper;
import com.github.houbb.web.common.dto.resp.BaseResp;
import com.github.houbb.web.common.dto.resp.BasePageInfo;
import com.github.houbb.web.common.util.RespUtil;
import com.github.houbb.privilege.api.annotation.PrivilegeAcquire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.houbb.sensitive.word.admin.service.service.WordLogService;
import com.github.houbb.sensitive.word.admin.dal.entity.WordLog;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordLogPagePo;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;



/**
 * <p>
 * 敏感词操作日志表 前端控制器
 * </p>
 *
 * @author dh
 * @since 2024-02-05
 */
@Controller
@RequestMapping("/wordLog")
@AutoLog
@Menu(id = "word-log", name = "敏感词操作日志表", orderNum = 0, type = "MENU", level = 1)
public class WordLogController {

    @Autowired
    private WordLogService wordLogService;

    /**
    * 首页信息
    * @return 结果
    */
    @RequestMapping("/index")
    @PrivilegeAcquire({"admin", "word-log-index"})
    @Menu(id = "word-log-index", pid = "word-log", name = "敏感词操作日志表-首页", orderNum = 0, type = "INDEX", level = 2)
    public String index() {
        return "wordLog/index";
    }

    /**
    * 添加元素
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/add")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-add"})
    @Menu(id = "word-log-add", pid = "word-log", name = "敏感词操作日志表-添加", orderNum = 1, type = "API", level = 2)
    public BaseResp add(@RequestBody final WordLog entity) {
        wordLogService.insert(entity);

        return RespUtil.success();
    }

    /**
    * 编辑
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/edit")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-edit"})
    @Menu(id = "word-log-edit", pid = "word-log", name = "敏感词操作日志表-编辑", orderNum = 2, type = "API", level = 2)
    public BaseResp edit(final WordLog entity) {
        wordLogService.updateById(entity);

        return RespUtil.success();
    }

    /**
    * 明细
    * @param id 主键
    * @return 结果
    */
    @RequestMapping("/detail/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-detail"})
    @Menu(id = "word-log-detail", pid = "word-log", name = "敏感词操作日志表-详情", orderNum = 3, type = "API", level = 2)
    public BaseResp detail(@PathVariable final Integer id) {
        WordLog entity = wordLogService.selectById(id);

        return RespUtil.of(entity);
    }

    /**
    * 删除
    * @param id 实体
    * @return 结果
    */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-remove"})
    @Menu(id = "word-log-remove", pid = "word-log", name = "敏感词操作日志表-删除", orderNum = 4, type = "API", level = 2)
    public BaseResp remove(@PathVariable final Integer id) {
        wordLogService.deleteById(id);
        return RespUtil.success();
    }

    /**
    * 列表
    * @param pageReq 入参
    * @return 结果
    */
    @RequestMapping("/list")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-list"})
    @Menu(id = "word-log-list", pid = "word-log", name = "敏感词操作日志表-列表", orderNum = 5, type = "API", level = 2)
    public BaseResp list(@RequestBody WordLogPagePo pageReq) {
        BasePageInfo<WordLog> pageInfo = wordLogService.pageQueryList(pageReq);
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
    @PrivilegeAcquire({"admin", "word-log-export"})
    @Menu(id = "word-log-export", pid = "word-log", name = "敏感词操作日志表-导出", orderNum = 6, type = "API", level = 2)
    public void export(@RequestBody WordLogPagePo pageReq, HttpServletResponse response) {
        final String fileName = "文件导出-敏感词操作日志表-" + System.currentTimeMillis() + ".xls";
        File file = new File(fileName);
        try {
            pageReq.setPageNum(1);
            pageReq.setPageSize(Integer.MAX_VALUE);

            BasePageInfo<WordLog> pageInfo = wordLogService.pageQueryList(pageReq);

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
            throw new RuntimeException(e);
        } finally {
            FileUtil.deleteFile(file);
        }
    }

    /**
    * 批量删除
    *
    * @param ids 唯一主键
    * @return 结果
    */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-log-deleteBatch"})
    @Menu(id = "word-log-deleteBatch", pid = "word-log", name = "敏感词操作日志表-批量删除", orderNum = 7, type = "API", level = 2)
    public BaseResp deleteBatch(@RequestBody List<Integer> ids) {
        wordLogService.deleteBatch(ids);
        return RespUtil.success();
    }
}
