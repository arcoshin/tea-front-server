package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.entity.Article;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleStandardVO;

import java.util.List;

/**
 * 处理文章数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IArticleRepository {

    /**
     * 设置文章的评论数
     *
     * @param articleId    文章ID
     * @param commentCount 评论数的新值
     * @return 受影响的行数
     */
    int setCommentCount(Long articleId, Integer commentCount);

    /**
     * 根据id修改文章数据
     *
     * @param article 封装了文章ID和新数据的对象
     * @return 受影响的行数
     */
    int update(Article article);

    /**
     * 根据id查询文章数据详情
     *
     * @param id 文章ID
     * @return 匹配的文章数据详情，如果没有匹配的数据，则返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查询文章列表
     *
     * @return 文章列表
     */
    List<ArticleListItemVO> list();

    /**
     * 查询文章列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据文章类别查询其文章列表
     *
     * @param categoryId 文章类别的ID
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

}
