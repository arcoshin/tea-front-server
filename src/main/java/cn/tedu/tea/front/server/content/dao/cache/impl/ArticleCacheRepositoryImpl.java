package cn.tedu.tea.front.server.content.dao.cache.impl;

import cn.tedu.tea.front.server.common.consts.CacheConsts;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.dao.cache.IArticleCacheRepository;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 處理文章數據的緩存的儲存庫實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class ArticleCacheRepositoryImpl implements IArticleCacheRepository, CacheConsts {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(List<ArticleListItemVO> articleListItemVOList) {
        log.debug("開始處理【向緩存中寫入文章列表】的數據訪問，參數：{}", articleListItemVOList);

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (ArticleListItemVO articleListItemVO : articleListItemVOList) {
            opsForList.rightPush(KEY_ARTICLE_LIST, articleListItemVO);
        }
    }

    @Override
    public boolean deleteList() {
        log.debug("開始處理【從緩存中刪除文章數據列表】的數據訪問，無參數");

        return redisTemplate.delete(KEY_ARTICLE_LIST);
    }

    @Override
    public List<ArticleListItemVO> list() {
        log.debug("開始處理【從緩存中讀取文章數據列表】的數據訪問，無參數");

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List<Serializable> range = opsForList.range(KEY_ARTICLE_LIST, 0, -1);
        List<ArticleListItemVO> articleListItemVOList = new ArrayList(range);
        return articleListItemVOList;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【從緩存中讀取文章數據列表】的數據訪問，頁碼：{}，每頁紀錄數：{}", pageNum, pageSize);

        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();

        /**
         * 利用頁碼以及每頁紀錄數聲明查詢緩存數據列表的下標
         * 頁碼pageNum    每頁紀錄數pageSize        ---------展示的緩存下標-----------
         *     1                5                    00                       04
         *     2                5                    05                       09
         *     3                5                    10                       14
         *  pageNum          pageSize      (pageNum-1)*pageSize      pageNum*pageSize-1
         */
        long startIndex = (pageNum - 1) * pageSize;
        long endIndex = pageNum * pageSize - 1;
        List<Serializable> range = opsForList.range(KEY_ARTICLE_LIST, startIndex, endIndex);
        List<ArticleListItemVO> articleListItemVOList = new ArrayList(range);

        long total = opsForList.size(KEY_ARTICLE_LIST);//獲取數據總數
        long maxPage = total / pageSize;//估算最大頁數=總數/每頁紀錄數
        if (total % pageSize != 0) maxPage += 1;//修正除不盡時，剩餘數據應使頁碼+1

        PageData<ArticleListItemVO> pageData = new PageData<>();
        pageData.setCurrentPage(pageNum)
                .setMaxPage((int) maxPage)
                .setPageSize(pageSize)
                .setTotal(total);

        return pageData;
    }
}
