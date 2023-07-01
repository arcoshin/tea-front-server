package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;

/**
 * --------------------------->Here<------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理標籤數據的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public interface ITagRepository {

    /**
     * 查詢標籤列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁紀錄數
     * @return 標籤列表的分頁數據
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);
}
