package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import cn.tedu.tea.front.server.content.service.ICommentService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 处理评论相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/comments")
@Validated
@Api(tags = "1.4. 内容管理-评论管理")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    public CommentController() {
        log.debug("创建控制器类对象：CommentController");
    }

    @PostMapping("/add-new")
    @ApiOperation("发表评论")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @ApiIgnore HttpServletRequest request,
                             @Valid CommentAddNewParam commentAddNewParam) {
        log.debug("开始处理【发布文章】的请求，参数：{}", commentAddNewParam);
        String remoteAddr = request.getRemoteAddr();
        commentService.addNew(currentPrincipal, remoteAddr, commentAddNewParam);
        return JsonResult.ok();
    }

    @GetMapping("/list-by-article")
    @ApiOperation("根据文章查询文章列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult listByArticleId(@Range(message = "请提交有效的文章ID值！") Long articleId,
                                      @Range(min = 1, message = "请提交有效的页码值！") Integer page) {
        log.debug("开始处理【根据文章查询评论列表】的请求，文章：{}，页码：{}", articleId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData = commentService.listByArticleId(articleId, pageNum);
        return JsonResult.ok(pageData);
    }

}