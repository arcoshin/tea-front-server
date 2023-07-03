package cn.tedu.tea.front.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类：内容-顶踩日志
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
@TableName("content_up_down_log")
public class UpDownLog implements Serializable {

    /**
     * 数据ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 资源类型，0=文章，1=评论
     */
    private Integer resourceType;
    /**
     * 资源ID
     */
    private Long resourceId;
    /**
     * 操作类型，0=踩，1=顶
     */
    private Integer opType;
    /**
     * 数据创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
