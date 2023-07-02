package cn.tedu.tea.front.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類：内容-評論
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
@TableName("content_comment")
public class Comment implements Serializable {

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
     * 文章ID
     */
    private Long articleId;

    /**
     * 評論內容
     */
    private String content ;

    /**
     * IP
     */
    private String ip   ;

    /**
     * 樓層
     */
    private Integer floor ;

    /**
     * 頂數量
     */
    private Integer upCount  ;

    /**
     * 踩數量
     */
    private Integer downCount ;

    /**
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState  ;

    /**
     * 審核原因
     */
    private String checkRemarks ;

    /**
     * 引用評論ID
     */
    private String referenceIds ;

    /**
     * 顯示狀態，0=不顯示，1=顯示
     */
    private Integer displayState ;

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
