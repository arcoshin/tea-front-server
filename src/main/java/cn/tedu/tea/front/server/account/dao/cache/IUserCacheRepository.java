package cn.tedu.tea.front.server.account.dao.cache;

import cn.tedu.tea.admin.server.common.pojo.po.UserLoginInfoPO;
import cn.tedu.tea.front.server.common.consts.UserCacheConsts;
import org.springframework.stereotype.Repository;

/**
 * 處理用戶緩存數據的存儲庫接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IUserCacheRepository extends UserCacheConsts {

    /**
     * 在Redis中刪除用戶登入訊息
     *
     * @param jwt 用戶登入成功後得到的JWT
     */
    void deleteLoginInfo(String jwt);

    /**
     * 在Redis中刪除用戶啟用狀態訊息
     *
     * @param userId 用戶id
     */
    void deleteEnable(Long userId);

    /**
     * 從Redis中讀取用戶登入訊息(根據JWT)
     *
     * @param jwt 用戶登入成功後得到的JWT
     * @return 與JWT匹配的用戶登錄信息，如果沒有匹配的數據，則返回null
     */
    UserLoginInfoPO getLoginInfo(String jwt);

    /**
     * 向Redis中讀取用戶啟用狀態訊息
     *
     * @param userId 用戶id
     * @return 與userId匹配的用戶啟用狀態信息，如果沒有匹配的數據，則返回null
     */
    Integer getEnableByUserId(Long userId);

}
