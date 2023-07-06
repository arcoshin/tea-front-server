package cn.tedu.tea.front.server.content.dao.persist.repository.impl;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.front.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.pojo.entity.Article;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章數據的存儲庫實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleRepositoryImpl() {
        log.info("創建存儲庫對象：ArticleRepositoryImpl");
    }

    @Override
    public int setCommentCount(Long articleId, Integer commentCount) {
        log.debug("開始執行【設置文章的評論數】的數據訪問，參數：{}", articleId);
        Article article = new Article();
        article.setId(articleId);
        article.setCommentCount(commentCount);
        return articleMapper.updateById(article);
    }

    @Override
    public int update(Article article) {
        log.debug("開始執行【更新文章】的數據訪問，參數：{}", article);
        return articleMapper.updateById(article);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢文章信息】的數據訪問，參數：{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public List<ArticleListItemVO> list() {
        log.debug("開始執行【查詢文章列表】的數據訪問，參數：無");
        List<ArticleListItemVO> list = articleMapper.list();
        return list;
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.list();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        PageData<ArticleListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，文章類別：{}，頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategoryId(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        PageData<ArticleListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}