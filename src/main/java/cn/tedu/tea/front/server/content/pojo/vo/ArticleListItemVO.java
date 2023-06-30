package cn.tedu.tea.front.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表項VO類：内容-文章
 *
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
public class ArticleListItemVO implements Serializable {
    /**
     * 數據ID
     */
    private Long id;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 類別ID
     */
    private Long categoryId;

    /**
     * 類別名稱
     */
    private String categoryName;

    /**
     * 標題
     */
    private String title;

    /**
     * 摘要
     */
    private String brief;

    /**
     * 標籤列表，實際存入JSON數據
     */
    private String tags;

    /**
     * 封面圖
     */
    private String coverUrl;

    /**
     * 頂數量
     */
    private Integer upCount;

    /**
     * 踩數量
     */
    private Integer downCount;

    /**
     * 瀏覽量
     */
    private Integer clickCount;

    /**
     * 評論量
     */
    private Integer commentCount;
}
