package cn.tedu.tea.front.server.content.dao.persist.mapper;

import cn.tedu.tea.front.server.content.pojo.entity.Tag;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理標籤數據的Mapper接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查詢標籤列表
     *
     * @return 標籤列表
     */
    List<TagListItemVO> list();
}
