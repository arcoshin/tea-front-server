package cn.tedu.tea.front.server.account.dao.cache.impl;

import cn.tedu.tea.front.server.account.dao.cache.IUserCacheRepository;
import cn.tedu.tea.front.server.common.pojo.po.UserLoginInfoPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 处理用户缓存数据的存储库实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UserCacheRepositoryImpl implements IUserCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public UserLoginInfoPO getLoginInfo(String jwt) {
        String key = USER_JWT_PREFIX + jwt;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable serializable = opsForValue.get(key);
        UserLoginInfoPO userLoginInfoPO = (UserLoginInfoPO) serializable;
        return userLoginInfoPO;
    }

    @Override
    public Integer getEnableByUserId(Long userId) {
        String key = USER_ENABLE_PREFIX + userId;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable serializable = opsForValue.get(key);
        Integer enable = (Integer) serializable;
        return enable;
    }

}