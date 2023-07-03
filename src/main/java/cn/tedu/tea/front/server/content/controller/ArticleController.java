package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 處理文章相關請求的控制器類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/articles")
@Validated
@Api(tags = "1.3. 內容管理-文章管理")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    public ArticleController() {
        log.debug("創建控制器類對象：ArticleController");
    }

    @PostMapping("/{id:[0-9]+}/up")
    @ApiOperation("頂文章")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult up(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                         @PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【頂文章】的請求，參數：{}", id);
        articleService.increaseUpCount(currentPrincipal, id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/down")
    @ApiOperation("踩文章")
    @ApiOperationSupport(order = 321)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult down(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                           @PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【踩文章】的請求，參數：{}", id);
        articleService.increaseDownCount(currentPrincipal, id);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢文章詳情")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(
            @PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【根據ID查詢文章詳情】的請求，參數：{}", id);
        ArticleStandardVO queryResult = articleService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @ApiOperation("查詢文章列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【查詢文章列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.list(pageNum);
        return JsonResult.ok(pageData);
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

}