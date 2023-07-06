package cn.tedu.tea.admin.server.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 用戶登入訊息的PO，此類數據將存入Redis中
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Data
public class UserLoginInfoPO implements Serializable {

    /**
     * 用戶登錄時使用的瀏覽器的信息
     */
    private String userAgent;

    /**
     * 用戶登錄時的IP地址
     */
    private String ip;

    /**
     * 權限列表的JSON字符串
     */
    private String authoritiesJsonString;
}
