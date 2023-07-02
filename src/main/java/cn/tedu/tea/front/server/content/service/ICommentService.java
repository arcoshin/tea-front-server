package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理評論數據的業務接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Transactional
public interface ICommentService {

    /**
     * 發布評論
     *
     * @param currentPrincipal   當事人
     * @param remoteAddress      IP地址
     * @param commentAddNewParam 新的評論數據
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddress, CommentAddNewParam commentAddNewParam);

    /**
     * 根據文章ID查詢評論列表，將使用默認的每頁記錄數
     *
     * @param articleId  文章ID
     * @param pageNum    頁碼
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum);

    /**
     * 根據文章ID查詢評論列表
     *
     * @param articleId  文章ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize);
}
