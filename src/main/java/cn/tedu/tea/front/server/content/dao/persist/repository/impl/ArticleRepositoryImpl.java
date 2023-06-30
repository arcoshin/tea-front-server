package cn.tedu.tea.front.server.content.dao.persist.repository.impl;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.front.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章數據的儲存庫實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，文章類別：{}，頁碼：{}，每頁紀錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategoryId(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        PageData<ArticleListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
}