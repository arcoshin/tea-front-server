package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.service.ICategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 處理類別相關請求的控制器類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/categories")
@Validated
@Api(tags = "1.1. 內容管理-類別管理")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        log.debug("創建控制器類對象：CategoryController");
    }

    @GetMapping("")
    @ApiOperation("查詢類別數據列表")
    @ApiOperationSupport(order = 420)
    public JsonResult list() {
        log.debug("開始處理【查詢類別數據列表】的請求，參數：無");
        List<CategoryListItemVO> categoryListItemVOList = categoryService.list();
        return JsonResult.ok(categoryListItemVOList);
    }

}