package cn.tedu.tea.front.server.content.dao.persist.mapper;

import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章數據的Mapper接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Repository
public interface ArticleMapper extends BaseMapper {

    /**
     * 根據文章類別（categoryId）查詢其文章列表
     *
     * @param categoryId 文章類別的ID（categoryId）
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategoryId(Long categoryId);

    /**
     * 查詢文章數據列表
     *
     * @return 文章數據列表
     */
    List<ArticleListItemVO> list();

}
