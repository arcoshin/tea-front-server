package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.entity.Article;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;

import java.util.List;

/**
 * 處理文章數據的存儲庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface IArticleRepository {

    /**
     * 設置文章的評論數
     *
     * @param articleId    文章ID
     * @param commentCount 評論數的新值
     * @return 受影響的行數
     */
    int setCommentCount(Long articleId, Integer commentCount);

    /**
     * 根據id修改文章數據
     *
     * @param article 封裝了文章ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Article article);

    /**
     * 根據id查詢文章數據詳情
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章列表
     *
     * @return 文章列表
     */
    List<ArticleListItemVO> list();

    /**
     * 查詢文章列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據文章類別查詢其文章列表
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

}
