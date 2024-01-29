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
import com.github.houbb.sensitive.word.admin.service.service.LcEnumMappingService;
import com.github.houbb.sensitive.word.admin.dal.entity.LcEnumMapping;
import com.github.houbb.sensitive.word.admin.dal.entity.po.LcEnumMappingPagePo;
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
 * 枚举映射表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2024-01-29
 */
@Controller
@RequestMapping("/lcEnumMapping")
@AutoLog
@Menu(id = "lc-enum-mapping", name = "枚举映射表", orderNum = 0, type = "MENU", level = 1)
public class LcEnumMappingController {

    @Autowired
    private LcEnumMappingService lcEnumMappingService;

    /**
    * 首页信息
    * @return 结果
    */
    @RequestMapping("/index")
    @PrivilegeAcquire({"admin", "lc-enum-mapping-index"})
    @Menu(id = "lc-enum-mapping-index", pid = "lc-enum-mapping", name = "枚举映射表-首页", orderNum = 0, type = "INDEX", level = 2)
    public String index() {
        return "lcEnumMapping/index";
    }

    /**
    * 添加元素
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/add")
    @ResponseBody
    @PrivilegeAcquire({"admin", "lc-enum-mapping-add"})
    @Menu(id = "lc-enum-mapping-add", pid = "lc-enum-mapping", name = "枚举映射表-添加", orderNum = 1, type = "API", level = 2)
    public BaseResp add(@RequestBody final LcEnumMapping entity) {
        lcEnumMappingService.insert(entity);

        return RespUtil.success();
    }

    /**
    * 编辑
    * @param entity 实体
    * @return 结果
    */
    @RequestMapping("/edit")
    @ResponseBody
    @PrivilegeAcquire({"admin", "lc-enum-mapping-edit"})
    @Menu(id = "lc-enum-mapping-edit", pid = "lc-enum-mapping", name = "枚举映射表-编辑", orderNum = 2, type = "API", level = 2)
    public BaseResp edit(final LcEnumMapping entity) {
        lcEnumMappingService.updateById(entity);

        return RespUtil.success();
    }

    /**
    * 明细
    * @param id 主键
    * @return 结果
    */
    @RequestMapping("/detail/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "lc-enum-mapping-detail"})
    @Menu(id = "lc-enum-mapping-detail", pid = "lc-enum-mapping", name = "枚举映射表-详情", orderNum = 3, type = "API", level = 2)
    public BaseResp detail(@PathVariable final Integer id) {
        LcEnumMapping entity = lcEnumMappingService.selectById(id);

        return RespUtil.of(entity);
    }

    /**
    * 删除
    * @param id 实体
    * @return 结果
    */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    @PrivilegeAcquire({"admin", "lc-enum-mapping-remove"})
    @Menu(id = "lc-enum-mapping-remove", pid = "lc-enum-mapping", name = "枚举映射表-删除", orderNum = 4, type = "API", level = 2)
    public BaseResp remove(@PathVariable final Integer id) {
        lcEnumMappingService.deleteById(id);
        return RespUtil.success();
    }

    /**
    * 列表
    * @param pageReq 入参
    * @return 结果
    */
    @RequestMapping("/list")
    @ResponseBody
    @PrivilegeAcquire({"admin", "lc-enum-mapping-list"})
    @Menu(id = "lc-enum-mapping-list", pid = "lc-enum-mapping", name = "枚举映射表-列表", orderNum = 5, type = "API", level = 2)
    public BaseResp list(@RequestBody LcEnumMappingPagePo pageReq) {
        BasePageInfo<LcEnumMapping> pageInfo = lcEnumMappingService.pageQueryList(pageReq);
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
    @PrivilegeAcquire({"admin", "lc-enum-mapping-export"})
    @Menu(id = "lc-enum-mapping-export", pid = "lc-enum-mapping", name = "枚举映射表-导出", orderNum = 6, type = "API", level = 2)
    public void export(@RequestBody LcEnumMappingPagePo pageReq, HttpServletResponse response) {
        final String fileName = "文件导出-枚举映射表-" + System.currentTimeMillis() + ".xls";
        File file = new File(fileName);
        try {
            pageReq.setPageNum(1);
            pageReq.setPageSize(Integer.MAX_VALUE);

            BasePageInfo<LcEnumMapping> pageInfo = lcEnumMappingService.pageQueryList(pageReq);

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
    @PrivilegeAcquire({"admin", "lc-enum-mapping-deleteBatch"})
    @Menu(id = "lc-enum-mapping-deleteBatch", pid = "lc-enum-mapping", name = "枚举映射表-批量删除", orderNum = 7, type = "API", level = 2)
    public BaseResp deleteBatch(@RequestBody List<Integer> ids) {
        lcEnumMappingService.deleteBatch(ids);
        return RespUtil.success();
    }
}
