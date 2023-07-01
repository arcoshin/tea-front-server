package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * --------------------------->Here<------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理分類數據的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface ICategoryRepository {

    /**
     * 查詢類別數據列表
     *
     * @return 類別數據列表
     */
    List<CategoryListItemVO> list();
}
