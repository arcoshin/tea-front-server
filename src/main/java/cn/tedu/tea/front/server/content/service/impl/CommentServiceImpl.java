package cn.tedu.tea.front.server.content.service.impl;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.ServiceCode;
import cn.tedu.tea.front.server.content.dao.cache.IArticleCacheRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import cn.tedu.tea.front.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import cn.tedu.tea.front.server.content.service.IArticleService;
import cn.tedu.tea.front.server.content.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 處理評論數據的業務實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Autowired
    private IArticleRepository articleRepository;
    @Autowired
    private ICommentRepository commentRepository;

    public CommentServiceImpl() {
        log.debug("創建業務類對象：CommentServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, String remoteAddress, CommentAddNewParam commentAddNewParam) {
        log.debug("開始處理【發布評論】的業務，當事人：{}，IP地址：{}，參數：{}", currentPrincipal, remoteAddress, commentAddNewParam);

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam,comment);
        comment.setAuthorId(currentPrincipal.getId());
        comment.setAuthorName(currentPrincipal.getUsername());
        comment.setIp(remoteAddress);
        comment.setFloor(articleRepository.getStandardById(commentAddNewParam.getArticleId()).getCommentCount());
        comment.setUpCount(0);
        comment.setDownCount(0);
        comment.setCheckState(0);
        comment.setDisplayState(0);

        int rows = commentRepository.insert(comment);
        if (rows != 1) {
            String message = "發布文章失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

    }

    @Override
    public PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum) {
        log.debug("開始處理【根據文章ID查詢評論列表】的業務，文章ID：{}, 頁碼：{}", articleId, pageNum);
        PageData<CommentListItemVO> pageData = commentRepository.listByArticleId(articleId, pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據文章ID查詢評論列表】的業務，文章ID：{}, 頁碼：{}，每頁記錄數：{}", articleId, pageNum, pageSize);
        PageData<CommentListItemVO> pageData = commentRepository.listByArticleId(articleId, pageNum, pageSize);
        return pageData;
    }
}
