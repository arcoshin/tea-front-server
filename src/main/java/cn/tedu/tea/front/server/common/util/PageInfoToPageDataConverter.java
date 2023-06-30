package cn.tedu.tea.front.server.common.util;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import com.github.pagehelper.PageInfo;

/**
 * 將PageInfo轉換成PageData的轉換器工具類
 *
 * @version 1.0
 */
public class PageInfoToPageDataConverter {
    /**
     * 類中使用泛型時
     * 當於普通方法中使用泛型T時，應在類名後加上<T>標籤
     * 當於靜態方法中使用泛型T時，應在類名static後加上<T>標籤
     */

    /**
     * @param pageInfo PageInfo對象
     * @param <T>      PageInfo對像中的列表數據中的元素數據的類型
     * @return         自定義的PageData類型的對象
     */
    public synchronized static <T> PageData convert(PageInfo<T> pageInfo) {
        PageData<T> pageData = new PageData<>();
        pageData.setPageSize(pageInfo.getPageSize())
                .setTotal(pageInfo.getTotal())
                .setCurrentPage(pageInfo.getPageNum())
                .setMaxPage(pageInfo.getPages())
                .setList(pageInfo.getList());
        return pageData;
    }
}
