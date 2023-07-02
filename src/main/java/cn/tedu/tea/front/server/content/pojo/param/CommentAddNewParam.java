package cn.tedu.tea.front.server.content.pojo.param;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 新增評論的參數類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class CommentAddNewParam implements Serializable {

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
     * 引用評論ID
     */
    private String referenceIds ;

}


