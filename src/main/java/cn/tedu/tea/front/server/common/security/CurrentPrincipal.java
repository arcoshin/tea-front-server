package cn.tedu.tea.front.server.common.security;

import lombok.Data;

import java.io.Serializable;

/**
 * 用於Security框架當前認證訊息中的自定義當事人格式
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Data
public class CurrentPrincipal implements Serializable {

    /**
     * 當事人ID
     */
    private Long id;

    /**
     * 當事人用戶名稱
     */
    private String username;
}
