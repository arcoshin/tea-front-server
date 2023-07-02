package cn.tedu.tea.front.server.content.service.impl;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.web.ServiceCode;
import cn.tedu.tea.front.server.content.dao.cache.IArticleCacheRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 處理文章數據的業務實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Autowired
    private IArticleRepository articleRepository;

    @Autowired
    private IArticleCacheRepository articleCacheRepository;

    public ArticleServiceImpl() {
        log.debug("創建業務類對象：ArticleServiceImpl");
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據文章類別查詢文章列表】的業務，文章類別：{}, 頁碼：{}", categoryId, pageNum);
        PageData<ArticleListItemVO> pageData = articleRepository.listByCategoryId(categoryId, pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據文章類別查詢文章列表】的業務，文章類別：{}, 頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageData<ArticleListItemVO> pageData = articleRepository.listByCategoryId(categoryId, pageNum, pageSize);
        return pageData;
    }

    @Override
    public List<ArticleListItemVO> list() {
        log.debug("開始處理【查詢文章數據列表】的業務，參數：無");

        List<ArticleListItemVO> articleListItemVOList = articleCacheRepository.list();
        return articleListItemVOList;
    }

    @Override
    public void rebuildListCache() {
        log.debug("開始處理【重建緩存中的文章列表】的業務，參數：無");

        articleCacheRepository.deleteList();
        List<ArticleListItemVO> list = articleCacheRepository.list();
        articleCacheRepository.save(list);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢文章詳情】的業務，參數：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查詢文章詳情失敗，文章數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }
}
