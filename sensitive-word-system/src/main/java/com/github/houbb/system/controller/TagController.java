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
import com.github.houbb.system.domain.Tag;
import com.github.houbb.system.service.ITagService;
import com.github.houbb.common.utils.poi.ExcelUtil;
import com.github.houbb.common.core.page.TableDataInfo;

/**
 * 标签Controller
 * 
 * @author lm
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/word/tag")
public class TagController extends BaseController
{
    @Autowired
    private ITagService tagService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('word:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tag tag)
    {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("@ss.hasPermi('word:tag:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tag tag)
    {
        List<Tag> list = tagService.selectTagList(tag);
        ExcelUtil<Tag> util = new ExcelUtil<Tag>(Tag.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('word:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tagService.selectTagById(id));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('word:tag:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tag tag)
    {
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("@ss.hasPermi('word:tag:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tag tag)
    {
        return toAjax(tagService.updateTag(tag));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('word:tag:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tagService.deleteTagByIds(ids));
    }
}
