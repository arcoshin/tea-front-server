package cn.tedu.tea.front.server.content.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 列表項VO類：内容-評論
 *
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
public class CommentListItemVO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

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
     * 引用評論ID
     */
    private String referenceIds ;

}
