package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import cn.tedu.tea.front.server.content.service.ICommentService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 處理文章相關請求的控制器類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/comments")
@Validated
@Api(tags = "1.4. 內容管理-評論管理")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    public CommentController() {
        log.debug("創建控制器類對象：CommentController");
    }

    @PostMapping("/add-new")
    @ApiOperation("發布評論")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @ApiIgnore HttpServletRequest request,
                             @Valid CommentAddNewParam commentAddNewParam) {
        log.debug("開始處理【發布評論】的請求，參數：{}", commentAddNewParam);
        String remoteAddress = request.getRemoteAddr();
        commentService.addNew(currentPrincipal, remoteAddress, commentAddNewParam);
        return JsonResult.ok();
    }

    @GetMapping("/list-by-article")
    @ApiOperation("根據文章ID查詢評論列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult listByArticleId(
            @Range(message = "請提交有效的文章類別ID值！") Long articleId,
            @Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【根據文章ID查詢評論列表】的請求，文章id：{}，頁碼：{}", articleId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData = commentService.listByArticleId(articleId, pageNum);
        return JsonResult.ok(pageData);
    }

}