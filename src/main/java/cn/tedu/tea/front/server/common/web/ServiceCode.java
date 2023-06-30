package cn.tedu.tea.front.server.common.web;

/**
 * 業務狀態代碼
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public enum ServiceCode {
    /**
     * 操作成功
     */
    OK(20000),

    /**
     * 錯誤:請求參數格式錯誤
     */
    ERROR_BAD_REQUEST(40000),

    /**
     * 錯誤:無法認證
     */
    ERROR_UNAUTHORIZED(40100),

    /**
     * 錯誤:無法認證，因為被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),

    /**
     * 錯誤:禁止訪問，用於無權限
     */
    ERROR_FORBIDDEN(40300),

    /**
     * 錯誤:數據不存在
     */
    ERROR_NOT_FOUND(40400),

    /**
     * 錯誤:數據衝突
     */
    ERROR_CONFLICT(40900),

    /**
     * 錯誤:未知的插入數據異常
     */
    ERROR_INSERT(50100),

    /**
     * 錯誤:未知的刪除數據異常
     */
    ERROR_DELETE(50200),

    /**
     * 錯誤:未知的修改數據異常
     */
    ERROR_UPDATE(50300),

    /**
     * 錯誤：JWT已過期
     */
    ERR_JWT_EXPIRED(60000),

    /**
     * 錯誤：JWT驗證簽名失敗，可能使用了偽造的JWT
     */
    ERR_JWT_SIGNATURE(60100),

    /**
     * 錯誤：JWT格式錯誤
     */
    ERR_JWT_MALFORMED(60200),

    /**
     * 錯誤：上傳的文件為空（沒有選擇有效的文件）
     */
    ERROR_UPLOAD_EMPTY(90000),

    /**
     * 錯誤：上傳的文件類型有誤
     */
    ERROR_UPLOAD_INVALID_TYPE(90100),

    /**
     * 錯誤：上傳的文件超出限制
     */
    ERROR_UPLOAD_EXCEED_MAX_SIZE(90200),

    /**
     * 錯誤:未知異常
     */
    ERROR_UNKNOWN(99999);

    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
