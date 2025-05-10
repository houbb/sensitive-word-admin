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
import com.github.houbb.system.domain.WordTagMapping;
import com.github.houbb.system.service.IWordTagMappingService;
import com.github.houbb.common.utils.poi.ExcelUtil;
import com.github.houbb.common.core.page.TableDataInfo;

/**
 * 标签单词映射Controller
 * 
 * @author lm
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/word/mapping")
public class WordTagMappingController extends BaseController
{
    @Autowired
    private IWordTagMappingService wordTagMappingService;

    /**
     * 查询标签单词映射列表
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:list')")
    @GetMapping("/list")
    public TableDataInfo list(WordTagMapping wordTagMapping)
    {
        startPage();
        List<WordTagMapping> list = wordTagMappingService.selectWordTagMappingList(wordTagMapping);
        return getDataTable(list);
    }

    /**
     * 导出标签单词映射列表
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:export')")
    @Log(title = "标签单词映射", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WordTagMapping wordTagMapping)
    {
        List<WordTagMapping> list = wordTagMappingService.selectWordTagMappingList(wordTagMapping);
        ExcelUtil<WordTagMapping> util = new ExcelUtil<WordTagMapping>(WordTagMapping.class);
        util.exportExcel(response, list, "标签单词映射数据");
    }

    /**
     * 获取标签单词映射详细信息
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wordTagMappingService.selectWordTagMappingById(id));
    }

    /**
     * 新增标签单词映射
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:add')")
    @Log(title = "标签单词映射", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WordTagMapping wordTagMapping)
    {
        return toAjax(wordTagMappingService.insertWordTagMapping(wordTagMapping));
    }

    /**
     * 修改标签单词映射
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:edit')")
    @Log(title = "标签单词映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WordTagMapping wordTagMapping)
    {
        return toAjax(wordTagMappingService.updateWordTagMapping(wordTagMapping));
    }

    /**
     * 删除标签单词映射
     */
    @PreAuthorize("@ss.hasPermi('word:mapping:remove')")
    @Log(title = "标签单词映射", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wordTagMappingService.deleteWordTagMappingByIds(ids));
    }
}
