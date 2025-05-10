package com.github.houbb.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.houbb.common.annotation.Log;
import com.github.houbb.common.core.controller.BaseController;
import com.github.houbb.common.core.domain.AjaxResult;
import com.github.houbb.common.enums.BusinessType;
import com.github.houbb.system.domain.WordLog;
import com.github.houbb.system.service.IWordLogService;
import com.github.houbb.common.utils.poi.ExcelUtil;
import com.github.houbb.common.core.page.TableDataInfo;

/**
 * 操作日志Controller
 * 
 * @author lm
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/word/log")
public class WordLogController extends BaseController
{
    @Autowired
    private IWordLogService wordLogService;

    /**
     * 查询操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('word:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(WordLog wordLog)
    {
        startPage();
        List<WordLog> list = wordLogService.selectWordLogList(wordLog);
        return getDataTable(list);
    }

    /**
     * 导出操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('word:log:export')")
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WordLog wordLog)
    {
        List<WordLog> list = wordLogService.selectWordLogList(wordLog);
        ExcelUtil<WordLog> util = new ExcelUtil<WordLog>(WordLog.class);
        util.exportExcel(response, list, "操作日志数据");
    }

    /**
     * 获取操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('word:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wordLogService.selectWordLogById(id));
    }

    /**
     * 新增操作日志
     */
    @PreAuthorize("@ss.hasPermi('word:log:add')")
    @Log(title = "操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WordLog wordLog)
    {
        return toAjax(wordLogService.insertWordLog(wordLog));
    }

    /**
     * 修改操作日志
     */
    @PreAuthorize("@ss.hasPermi('word:log:edit')")
    @Log(title = "操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WordLog wordLog)
    {
        return toAjax(wordLogService.updateWordLog(wordLog));
    }

    /**
     * 删除操作日志
     */
    @PreAuthorize("@ss.hasPermi('word:log:remove')")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wordLogService.deleteWordLogByIds(ids));
    }
}
