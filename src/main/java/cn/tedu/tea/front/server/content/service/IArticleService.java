package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理文章數據的業務接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Transactional
public interface IArticleService {

    /**
     * 根據文章類別查詢文章列表，將使用默認的每頁記錄數
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum);

    /**
     * 根據文章類別查詢文章列表
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);
}
