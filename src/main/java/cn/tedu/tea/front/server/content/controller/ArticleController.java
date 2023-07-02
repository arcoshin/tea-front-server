package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 處理文章相關請求的控制器類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/articles")
@Validated
@Api(tags = "2.1. 內容管理-文章管理")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    public ArticleController() {
        log.debug("創建控制器類對象：ArticleController");
    }

    @GetMapping("/list-by-category")
    @ApiOperation("根據文章類別查詢文章列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "文章類別ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult listByCategoryId(@Range(message = "請提交有效的文章類別ID值！") Long categoryId,
                                       @Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【根據文章類別查詢文章列表】的請求，父級文章：{}，頁碼：{}", categoryId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.listByCategoryId(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("")
    @ApiOperation("查詢文章數據列表")
    @ApiOperationSupport(order = 422)
    public JsonResult list() {
        log.debug("開始處理【查詢文章數據列表】的請求，參數：無");
        List<ArticleListItemVO> articleListItemVOList = articleService.list();
        return JsonResult.ok(articleListItemVOList);
    }

}