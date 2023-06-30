package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;

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

}
