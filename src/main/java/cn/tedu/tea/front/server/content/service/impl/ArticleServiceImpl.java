package cn.tedu.tea.front.server.content.service.impl;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.ServiceCode;
import cn.tedu.tea.front.server.content.dao.cache.IArticleCacheRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.tea.front.server.content.pojo.entity.Article;
import cn.tedu.tea.front.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.UpDownLogStandardVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.tedu.tea.front.server.common.consts.ContentConsts.*;

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
    @Autowired
    private IUpDownLogRepository upDownLogRepository;

    public ArticleServiceImpl() {
        log.debug("創建業務類對象：ArticleServiceImpl");
    }

    @Override
    public void increaseUpCount(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("開始處理【增加文章的\"頂\"數量】的業務，當事人：{}, 文章：{}", currentPrincipal, id);
        increaseUpOrDownCount(currentPrincipal.getId(), id, OP_TYPE_UP);
    }

    @Override
    public void increaseDownCount(CurrentPrincipal currentPrincipal, Long id) {
        log.debug("開始處理【增加文章的\"踩\"數量】的業務，當事人：{}, 文章：{}", currentPrincipal, id);
        increaseUpOrDownCount(currentPrincipal.getId(), id, OP_TYPE_DOWN);
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

    @Override
    public List<ArticleListItemVO> list() {
        log.debug("開始處理【查詢文章列表】的業務，參數：無");
        return articleCacheRepository.list();
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢文章列表】的業務，, 頁碼：{}", pageNum);
        PageData<ArticleListItemVO> pageData = articleCacheRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢文章列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageData<ArticleListItemVO> pageData = articleCacheRepository.list(pageNum, pageSize);
        return pageData;
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
    public void rebuildListCache() {
        log.debug("開始處理【重建緩存中的文章列表】的業務，參數：無");
        articleCacheRepository.deleteList();
        List<ArticleListItemVO> list = articleRepository.list();
        articleCacheRepository.save(list);
    }

    private void increaseUpOrDownCount(Long userId, Long articleId, Integer opType) {
        ArticleStandardVO currentArticle = articleRepository.getStandardById(articleId);
        if (currentArticle == null) {
            String message = "操作失敗，文章數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        UpDownLogStandardVO queryResult = upDownLogRepository.getByUserAndResource(userId, RESOURCE_TYPE_ARTICLE, articleId);

        if (queryResult != null) {
            if (queryResult.getOpType() == opType) {
                log.debug("當前用戶已經【{}】過此文章，將不執行任何操作，直接結束！", UP_DOWN_TEXT[opType]);
                String message = "操作失敗，您此前已經" + UP_DOWN_TEXT[opType] + "過此文章！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            } else {
                log.debug("當前用戶此前【{}】過此文章，將刪除此前的記錄！", UP_DOWN_TEXT[(opType + 1) % 2]);
                int rows = upDownLogRepository.deleteByUserAndResource(userId, RESOURCE_TYPE_ARTICLE, articleId);
                if (rows != 1) {
                    String message = "操作失敗，服務器忙，請稍後再嘗試！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERROR_DELETE, message);
                }
                Article article = new Article();
                article.setId(articleId);
                if (OP_TYPE_UP == opType) {
                    article.setDownCount(currentArticle.getDownCount() - 1);
                } else {
                    article.setUpCount(currentArticle.getUpCount() - 1);
                }
                rows = articleRepository.update(article);
                if (rows != 1) {
                    String message = "操作失敗，服務器忙，請稍後再嘗試！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
                }
            }
        }

        UpDownLog upDownLog = new UpDownLog();
        upDownLog.setUserId(userId);
        upDownLog.setResourceType(RESOURCE_TYPE_ARTICLE);
        upDownLog.setResourceId(articleId);
        upDownLog.setOpType(opType);
        int rows = upDownLogRepository.insert(upDownLog);
        if (rows != 1) {
            String message = "操作失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Article article = new Article();
        article.setId(articleId);
        if (OP_TYPE_UP == opType) {
            article.setUpCount(currentArticle.getUpCount() + 1);
        } else {
            article.setDownCount(currentArticle.getDownCount() + 1);
        }
        rows = articleRepository.update(article);
        if (rows != 1) {
            String message = "操作失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }
}
