package cn.tedu.tea.front.server.account.dao.cache.impl;

import cn.tedu.tea.admin.server.common.pojo.po.UserLoginInfoPO;
import cn.tedu.tea.front.server.account.dao.cache.IUserCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UserCacheRepositoryImpl implements IUserCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void deleteLoginInfo(String jwt) {
        String key = USER_JWT_PREFIX + jwt;
        redisTemplate.delete(key);
    }

    @Override
    public void deleteEnable(Long userId) {
        String key = USER_ENABLE_PREFIX + userId;
        redisTemplate.delete(key);
    }

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
