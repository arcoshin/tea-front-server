package cn.tedu.tea.front.server.content.dao.persist.repository.impl;

import cn.tedu.tea.front.server.content.dao.persist.mapper.UpDownLogMapper;
import cn.tedu.tea.front.server.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.tea.front.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.front.server.content.pojo.vo.UpDownLogStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 处理顶踩日志数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UpDownDetailRepositoryImpl implements IUpDownLogRepository {

    @Autowired
    private UpDownLogMapper upDownLogMapper;

    public UpDownDetailRepositoryImpl() {
        log.info("创建存储库对象：UpDownDetailRepositoryImpl");
    }

    @Override
    public int insert(UpDownLog upDownLog) {
        log.debug("开始执行【插入顶踩日志数据】的数据访问，参数：{}", upDownLog);
        return upDownLogMapper.insert(upDownLog);
    }

    @Override
    public int deleteByUserAndResource(Long userId, Integer resourceType, Long resourceId) {
        log.debug("开始执行【根据用户与资源删除顶踩记录】的数据访问，用户ID：{}，资源类型：{}，资源ID：{}", userId, resourceType, resourceId);
        QueryWrapper<UpDownLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_id", resourceId);
        return upDownLogMapper.delete(queryWrapper);
    }

    @Override
    public int update(UpDownLog upDownLog) {
        log.debug("开始执行【根据ID修改顶踩日志数据】的数据访问，参数：{}", upDownLog);
        return upDownLogMapper.updateById(upDownLog);
    }

    @Override
    public int countByUserAndResource(Long userId, Integer resourceType, Long resourceId) {
        log.debug("开始执行【根据用户与资源统计顶踩记录】的数据访问，用户ID：{}，资源类型：{}，资源ID：{}", userId, resourceType, resourceId);
        QueryWrapper<UpDownLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_id", resourceId);
        return upDownLogMapper.selectCount(queryWrapper);
    }

    @Override
    public UpDownLogStandardVO getByUserAndResource(Long userId, Integer resourceType, Long resourceId) {
        log.debug("开始执行【根据用户与资源查询顶踩记录】的数据访问，用户ID：{}，资源类型：{}，资源ID：{}", userId, resourceType, resourceId);
        return upDownLogMapper.getByUserAndResource(userId, resourceType, resourceId);
    }

}