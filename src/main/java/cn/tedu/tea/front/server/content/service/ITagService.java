package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * -------------->Here<-------------------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理標籤數據的業務接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Transactional
public interface ITagService {

    /**
     * 查詢標籤列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 標籤列表
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查詢標籤列表，將使用默認的"每頁紀錄數"
     *
     * @param pageNum  頁碼
     * @return 標籤列表
     */
    PageData<TagListItemVO> list(Integer pageNum);
}
