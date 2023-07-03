package cn.tedu.tea.front.server.content.dao.persist.mapper;

import cn.tedu.tea.front.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.front.server.content.pojo.vo.UpDownLogStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理顶踩日志数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Repository
public interface UpDownLogMapper extends BaseMapper<UpDownLog> {

    /**
     * 根据ID查询顶踩日志
     *
     * @param id 顶踩日志ID
     * @return 匹配的顶踩日志，如果没有匹配的数据，则返回null
     */
    UpDownLogStandardVO getStandardById(Long id);

    /**
     * 根据用户与资源查询顶踩记录，一般用于检查用户是否已经顶或踩过某个资源
     *
     * @param userId       用户ID
     * @param resourceType 资源类型
     * @param resourceId   资源ID
     * @return 统计结果
     * @see cn.tedu.tea.front.server.common.consts.ContentConsts
     */
    UpDownLogStandardVO getByUserAndResource(Long userId, Integer resourceType, Long resourceId);

}