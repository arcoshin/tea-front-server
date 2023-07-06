package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 处理文章相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/articles")
@Validated
@Api(tags = "1.3. 内容管理-文章管理")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    public ArticleController() {
        log.debug("创建控制器类对象：ArticleController");
    }

    @PostMapping("/{id:[0-9]+}/up")
    @ApiOperation("顶文章")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult up(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                         @PathVariable @Range(min = 1, message = "请提交有效的文章ID值！") Long id) {
        log.debug("开始处理【顶文章】的请求，参数：{}", id);
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
                           @PathVariable @Range(min = 1, message = "请提交有效的文章ID值！") Long id) {
        log.debug("开始处理【踩文章】的请求，参数：{}", id);
        articleService.increaseDownCount(currentPrincipal, id);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根据ID查询文章详情")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(
            @PathVariable @Range(min = 1, message = "请提交有效的文章ID值！") Long id) {
        log.debug("开始处理【根据ID查询文章详情】的请求，参数：{}", id);
        ArticleStandardVO queryResult = articleService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @ApiOperation("查询文章列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult list(@Range(min = 1, message = "请提交有效的页码值！") Integer page) {
        log.debug("开始处理【查询文章列表】的请求，页码：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.list(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-category")
    @ApiOperation("根据文章类别查询文章列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "文章类别ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult listByCategoryId(@Range(message = "请提交有效的文章类别ID值！") Long categoryId,
                                       @Range(min = 1, message = "请提交有效的页码值！") Integer page) {
        log.debug("开始处理【根据文章类别查询文章列表】的请求，父级文章：{}，页码：{}", categoryId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.listByCategoryId(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

}
