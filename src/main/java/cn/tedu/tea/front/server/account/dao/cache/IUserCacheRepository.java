package cn.tedu.tea.front.server.account.dao.cache;

import cn.tedu.tea.front.server.common.consts.UserCacheConsts;
import cn.tedu.tea.front.server.common.pojo.po.UserLoginInfoPO;

/**
 *
 */
public interface IUserCacheRepository extends UserCacheConsts {

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
