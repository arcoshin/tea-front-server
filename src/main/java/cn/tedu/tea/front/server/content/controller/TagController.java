package cn.tedu.tea.front.server.content.controller;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import cn.tedu.tea.front.server.content.service.ITagService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 處理標籤相關請求的控制器類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/content/tags")
@Validated
@Api(tags = "1.2. 內容管理-標籤管理")
public class TagController {

    @Autowired
    private ITagService tagService;

    public TagController() {
        log.info("創建控制器對象：TagController");
    }

    @ApiOperation("查詢標籤列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型，當需要查詢全部數據時，此參數值應該是all")
    })
    @GetMapping("")
    public JsonResult list(Integer page, String queryType) {
        log.debug("開始處理【查詢標籤列表】請求，頁碼：{}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<TagListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = tagService.list(1, Integer.MAX_VALUE);
        } else {
            pageData = tagService.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}