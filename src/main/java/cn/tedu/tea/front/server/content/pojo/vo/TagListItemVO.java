package cn.tedu.tea.front.server.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表項VO類：内容-標籤
 *
 * @author XJX@tedu.cn
 * @version 0.0.1
 */
@Data
public class TagListItemVO implements Serializable {
    /**
     * 數據ID
     */
    private Long id;

    /**
     * 類別名稱
     */
    private String name;
}