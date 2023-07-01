package cn.tedu.tea.front.server.content.dao.cache.impl;

import cn.tedu.tea.front.server.common.consts.CacheConsts;
import cn.tedu.tea.front.server.content.dao.cache.ICategoryCacheRepository;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 處理類別數據的緩存的儲存庫實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class CategoryCacheRepositoryImpl implements ICategoryCacheRepository, CacheConsts {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(List<CategoryListItemVO> categoryListItemVOList) {
        log.debug("開始處理【向緩存中寫入類別列表】的數據訪問，參數：{}", categoryListItemVOList);

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (CategoryListItemVO categoryListItemVO : categoryListItemVOList) {
            opsForList.rightPush(KEY_CATEGORY_LIST, categoryListItemVO);
        }
    }

    @Override
    public boolean deleteList() {
        log.debug("開始處理【從緩存中刪除類別數據列表】的數據訪問，無參數");

        return redisTemplate.delete(KEY_CATEGORY_LIST);
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始處理【從緩存中讀取類別數據列表】的數據訪問，無參數");

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List<Serializable> range = opsForList.range(KEY_CATEGORY_LIST, 0, -1);
        List<CategoryListItemVO> categoryListItemVOList = new ArrayList(range);
        return categoryListItemVOList;
    }
}
