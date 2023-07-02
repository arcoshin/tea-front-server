package cn.tedu.tea.front.server.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 標準VO類：内容-文章
 *
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
public class ArticleStandardVO implements Serializable {
    /**
     * 數據ID
     */
    private Long id;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 類別ID
     */
    private Long categoryId;

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
     * 排序序號
     */
    private Integer sort;

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

    /**
     * 數據創建時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    /**
     * 數據最後修改時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

}
