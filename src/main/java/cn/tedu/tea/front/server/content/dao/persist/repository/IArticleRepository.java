package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * 處理文章數據的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface IArticleRepository {

    /**
     * 根據文章類別查詢其文章列表
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁紀錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * 查詢文章列表
     *
     * @param pageNum    頁碼
     * @param pageSize   每頁紀錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據id查詢文章數據詳情
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);
}
