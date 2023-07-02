package cn.tedu.tea.front.server.content.dao.persist.repository.impl;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.front.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.front.server.content.dao.persist.mapper.CommentMapper;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;
import cn.tedu.tea.front.server.content.pojo.vo.CommentListItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論數據的儲存庫實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public CommentRepositoryImpl() {
        log.info("創建儲存庫對象：CommentRepositoryImpl");
    }


    @Override
    public int insert(Comment comment) {
        log.debug("開始執行【插入評論】的數據訪問，參數：{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public PageData<CommentListItemVO> listByArticleId(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據文章ID查詢評論列表】的數據訪問，文章類別：{}，頁碼：{}，每頁紀錄數：{}", articleId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByArticleId(articleId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        PageData<CommentListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
}
