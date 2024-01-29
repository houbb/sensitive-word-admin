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
import com.github.houbb.sensitive.word.admin.service.service.WordTagMappingService;
import com.github.houbb.sensitive.word.admin.dal.entity.WordTagMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.WordTagMappingPagePo;
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
 * 标签单词映射表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
@Controller
@RequestMapping("/wordTagMapping")
@AutoLog
@Menu(id = "word-tag-mapping", name = "标签单词映射表", orderNum = 0, type = "MENU", level = 1)
public class WordTagMappingController {

    @Autowired
    private WordTagMappingService wordTagMappingService;

    /**
    * 首页信息
    * @return 结果
    */
    @RequestMapping("/index")
    @PrivilegeAcquire({"admin", "word-tag-mapping-index"})
    @Menu(id = "word-tag-mapping-index", pid = "word-tag-mapping", name = "标签单词映射表-首页", orderNum = 0, type = "INDEX", level = 2)
    public String index() {
        return "wordTagMapping/index";
    }

    /**
    * 添加元素
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/add")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-tag-mapping-add"})
    @Menu(id = "word-tag-mapping-add", pid = "word-tag-mapping", name = "标签单词映射表-添加", orderNum = 1, type = "API", level = 2)
    public BaseResp add(@RequestBody final WordTagMapping entity) {
        wordTagMappingService.insert(entity);

        return RespUtil.success();
    }

    /**
    * 编辑
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/edit")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-tag-mapping-edit"})
    @Menu(id = "word-tag-mapping-edit", pid = "word-tag-mapping", name = "标签单词映射表-编辑", orderNum = 2, type = "API", level = 2)
    public BaseResp edit(final WordTagMapping entity) {
        wordTagMappingService.updateById(entity);

        return RespUtil.success();
    }

    /**
    * 明细
    * @param id 主键
    * @return 结果
    */
    @RequestMapping("/detail/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-tag-mapping-detail"})
    @Menu(id = "word-tag-mapping-detail", pid = "word-tag-mapping", name = "标签单词映射表-详情", orderNum = 3, type = "API", level = 2)
    public BaseResp detail(@PathVariable final Integer id) {
        WordTagMapping entity = wordTagMappingService.selectById(id);

        return RespUtil.of(entity);
    }

    /**
    * 删除
    * @param id 实体
    * @return 结果
    */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-tag-mapping-remove"})
    @Menu(id = "word-tag-mapping-remove", pid = "word-tag-mapping", name = "标签单词映射表-删除", orderNum = 4, type = "API", level = 2)
    public BaseResp remove(@PathVariable final Integer id) {
        wordTagMappingService.deleteById(id);
        return RespUtil.success();
    }

    /**
    * 列表
    * @param pageReq 入参
    * @return 结果
    */
    @RequestMapping("/list")
    @ResponseBody
    @PrivilegeAcquire({"admin", "word-tag-mapping-list"})
    @Menu(id = "word-tag-mapping-list", pid = "word-tag-mapping", name = "标签单词映射表-列表", orderNum = 5, type = "API", level = 2)
    public BaseResp list(@RequestBody WordTagMappingPagePo pageReq) {
        BasePageInfo<WordTagMapping> pageInfo = wordTagMappingService.pageQueryList(pageReq);
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
    @PrivilegeAcquire({"admin", "word-tag-mapping-export"})
    @Menu(id = "word-tag-mapping-export", pid = "word-tag-mapping", name = "标签单词映射表-导出", orderNum = 6, type = "API", level = 2)
    public void export(@RequestBody WordTagMappingPagePo pageReq, HttpServletResponse response) {
        final String fileName = "文件导出-标签单词映射表-" + System.currentTimeMillis() + ".xls";
        File file = new File(fileName);
        try {
            pageReq.setPageNum(1);
            pageReq.setPageSize(Integer.MAX_VALUE);

            BasePageInfo<WordTagMapping> pageInfo = wordTagMappingService.pageQueryList(pageReq);

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
    @PrivilegeAcquire({"admin", "word-tag-mapping-deleteBatch"})
    @Menu(id = "word-tag-mapping-deleteBatch", pid = "word-tag-mapping", name = "标签单词映射表-批量删除", orderNum = 7, type = "API", level = 2)
    public BaseResp deleteBatch(@RequestBody List<Integer> ids) {
        wordTagMappingService.deleteBatch(ids);
        return RespUtil.success();
    }
}
