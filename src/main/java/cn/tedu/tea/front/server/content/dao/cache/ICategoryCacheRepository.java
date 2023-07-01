package cn.tedu.tea.front.server.content.dao.cache;

import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * 處理類別數據的緩存的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface ICategoryCacheRepository {

    /**
     * 向緩存中寫入類別數據列表
     *
     * @param categoryListItemVOList 類別數據列表
     */
    void save(List<CategoryListItemVO> categoryListItemVOList);

    /**
     * 向緩存中刪除類別數據列表
     *
     * @return 刪除成功則返回true，反之false
     */
    boolean deleteList();

    /**
     * 從緩存中讀取類別數據列表
     *
     * @return 類別數據列表
     */
    List<CategoryListItemVO> list();
}
