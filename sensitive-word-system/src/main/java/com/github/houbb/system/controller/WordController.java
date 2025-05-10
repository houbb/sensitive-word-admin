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
import com.github.houbb.system.domain.Word;
import com.github.houbb.system.service.IWordService;
import com.github.houbb.common.utils.poi.ExcelUtil;
import com.github.houbb.common.core.page.TableDataInfo;

/**
 * 敏感词Controller
 * 
 * @author lm
 * @date 2025-05-10
 */
@RestController
@RequestMapping("/word/word")
public class WordController extends BaseController
{
    @Autowired
    private IWordService wordService;

    /**
     * 查询敏感词列表
     */
    @PreAuthorize("@ss.hasPermi('word:word:list')")
    @GetMapping("/list")
    public TableDataInfo list(Word word)
    {
        startPage();
        List<Word> list = wordService.selectWordList(word);
        return getDataTable(list);
    }

    /**
     * 导出敏感词列表
     */
    @PreAuthorize("@ss.hasPermi('word:word:export')")
    @Log(title = "敏感词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Word word)
    {
        List<Word> list = wordService.selectWordList(word);
        ExcelUtil<Word> util = new ExcelUtil<Word>(Word.class);
        util.exportExcel(response, list, "敏感词数据");
    }

    /**
     * 获取敏感词详细信息
     */
    @PreAuthorize("@ss.hasPermi('word:word:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wordService.selectWordById(id));
    }

    /**
     * 新增敏感词
     */
    @PreAuthorize("@ss.hasPermi('word:word:add')")
    @Log(title = "敏感词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Word word)
    {
        return toAjax(wordService.insertWord(word));
    }

    /**
     * 修改敏感词
     */
    @PreAuthorize("@ss.hasPermi('word:word:edit')")
    @Log(title = "敏感词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Word word)
    {
        return toAjax(wordService.updateWord(word));
    }

    /**
     * 删除敏感词
     */
    @PreAuthorize("@ss.hasPermi('word:word:remove')")
    @Log(title = "敏感词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wordService.deleteWordByIds(ids));
    }
}
