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
import com.github.houbb.sensitive.word.admin.service.service.TagService;
import com.github.houbb.sensitive.word.admin.dal.entity.Tag;
import com.github.houbb.sensitive.word.admin.dal.entity.po.TagPagePo;
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
 * 标签表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
@Controller
@RequestMapping("/tag")
@AutoLog
@Menu(id = "tag", name = "标签表", orderNum = 0, type = "MENU", level = 1)
public class TagController {

    @Autowired
    private TagService tagService;

    /**
    * 首页信息
    * @return 结果
    */
    @RequestMapping("/index")
    @PrivilegeAcquire({"admin", "tag-index"})
    @Menu(id = "tag-index", pid = "tag", name = "标签表-首页", orderNum = 0, type = "INDEX", level = 2)
    public String index() {
        return "tag/index";
    }

    /**
    * 添加元素
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/add")
    @ResponseBody
    @PrivilegeAcquire({"admin", "tag-add"})
    @Menu(id = "tag-add", pid = "tag", name = "标签表-添加", orderNum = 1, type = "API", level = 2)
    public BaseResp add(@RequestBody final Tag entity) {
        tagService.insert(entity);

        return RespUtil.success();
    }

    /**
    * 编辑
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/edit")
    @ResponseBody
    @PrivilegeAcquire({"admin", "tag-edit"})
    @Menu(id = "tag-edit", pid = "tag", name = "标签表-编辑", orderNum = 2, type = "API", level = 2)
    public BaseResp edit(final Tag entity) {
        tagService.updateById(entity);

        return RespUtil.success();
    }

    /**
    * 明细
    * @param id 主键
    * @return 结果
    */
    @RequestMapping("/detail/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "tag-detail"})
    @Menu(id = "tag-detail", pid = "tag", name = "标签表-详情", orderNum = 3, type = "API", level = 2)
    public BaseResp detail(@PathVariable final Integer id) {
        Tag entity = tagService.selectById(id);

        return RespUtil.of(entity);
    }

    /**
    * 删除
    * @param id 实体
    * @return 结果
    */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "tag-remove"})
    @Menu(id = "tag-remove", pid = "tag", name = "标签表-删除", orderNum = 4, type = "API", level = 2)
    public BaseResp remove(@PathVariable final Integer id) {
        tagService.deleteById(id);
        return RespUtil.success();
    }

    /**
    * 列表
    * @param pageReq 入参
    * @return 结果
    */
    @RequestMapping("/list")
    @ResponseBody
    @PrivilegeAcquire({"admin", "tag-list"})
    @Menu(id = "tag-list", pid = "tag", name = "标签表-列表", orderNum = 5, type = "API", level = 2)
    public BaseResp list(@RequestBody TagPagePo pageReq) {
        BasePageInfo<Tag> pageInfo = tagService.pageQueryList(pageReq);
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
    @PrivilegeAcquire({"admin", "tag-export"})
    @Menu(id = "tag-export", pid = "tag", name = "标签表-导出", orderNum = 6, type = "API", level = 2)
    public void export(@RequestBody TagPagePo pageReq, HttpServletResponse response) {
        final String fileName = "文件导出-标签表-" + System.currentTimeMillis() + ".xls";
        File file = new File(fileName);
        try {
            pageReq.setPageNum(1);
            pageReq.setPageSize(Integer.MAX_VALUE);

            BasePageInfo<Tag> pageInfo = tagService.pageQueryList(pageReq);

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
    @PrivilegeAcquire({"admin", "tag-deleteBatch"})
    @Menu(id = "tag-deleteBatch", pid = "tag", name = "标签表-批量删除", orderNum = 7, type = "API", level = 2)
    public BaseResp deleteBatch(@RequestBody List<Integer> ids) {
        tagService.deleteBatch(ids);
        return RespUtil.success();
    }
}
