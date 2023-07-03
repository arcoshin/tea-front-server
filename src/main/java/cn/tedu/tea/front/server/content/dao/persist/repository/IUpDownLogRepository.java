package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.front.server.content.pojo.vo.UpDownLogStandardVO;

/**
 * 处理顶踩日志数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IUpDownLogRepository {

    /**
     * 插入顶踩日志数据
     *
     * @param upDownLog 顶踩日志数据
     * @return 受影响的行数
     */
    int insert(UpDownLog upDownLog);

    /**
     * 根据用户与资源删除顶踩记录
     *
     * @param userId       用户ID
     * @param resourceType 资源类型
     * @param resourceId   资源ID
     * @return 受影响的行数
     * @see cn.tedu.tea.admin.server.common.consts.ContentConsts
     */
    int deleteByUserAndResource(Long userId, Integer resourceType, Long resourceId);

    /**
     * 根据ID修改顶踩日志数据
     *
     * @param upDownLog 封装了ID和新数据的对象
     * @return 受影响的行数
     * @see cn.tedu.tea.front.server.common.consts.ContentConsts
     */
    int update(UpDownLog upDownLog);

    /**
     * 根据用户与资源统计顶踩记录，一般用于检查用户是否已经顶或踩过某个资源
     *
     * @param userId       用户ID
     * @param resourceType 资源类型
     * @param resourceId   资源ID
     * @return 统计结果
     * @see cn.tedu.tea.front.server.common.consts.ContentConsts
     */
    int countByUserAndResource(Long userId, Integer resourceType, Long resourceId);

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
