package cn.tedu.tea.front.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類：内容-文章
 *
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
@TableName("content_article")
public class Article implements Serializable {
    /**
     * 數據ID
     */
    @TableId(type = IdType.AUTO)
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
     * IP
     */
    private String ip;

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
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState;

    /**
     * 審核原因
     */
    private String checkRemarks;

    /**
     * 顯示狀態，0=不顯示，1=顯示
     */
    private Integer displayState;

    /**
     * 數據創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
