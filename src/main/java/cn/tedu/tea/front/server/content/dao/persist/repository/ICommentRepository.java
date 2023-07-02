package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;

/**
 * 處理評論數據的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface ICommentRepository {

    /**
     * 插入評論數據
     *
     * @param comment 評論數據
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據文章ID查詢其評論列表
     *
     * @param articleId  文章的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁紀錄數
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize);
}
