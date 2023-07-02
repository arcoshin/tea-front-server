package cn.tedu.tea.front.server.content.dao.cache;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;

import java.util.List;

/**
 * 處理文章數據的緩存的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface IArticleCacheRepository {

    /**
     * 向緩存中寫入文章數據列表
     *
     * @param articleListItemVOList 文章數據列表
     */
    void save(List<ArticleListItemVO> articleListItemVOList);

    /**
     * 向緩存中刪除文章數據列表
     *
     * @return 刪除成功則返回true，反之false
     */
    boolean deleteList();

    /**
     * 從緩存中讀取文章數據列表
     *
     * @return 文章數據列表
     */
    List<ArticleListItemVO> list();

    /**
     * 查詢文章列表
     *
     * @param pageNum    頁碼
     * @param pageSize   每頁紀錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

}
