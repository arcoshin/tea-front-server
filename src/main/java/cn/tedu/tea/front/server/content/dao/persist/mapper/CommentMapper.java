package cn.tedu.tea.front.server.content.dao.persist.mapper;

import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論數據的Mapper接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據文章ID查詢評論列表
     *
     * @param articleId 文章ID
     * @return 評論列表
     */
    List<CommentListItemVO> listByArticleId(Long articleId);
}
