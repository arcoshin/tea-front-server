package cn.tedu.tea.front.server.common.web;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import lombok.Data;

/**
 * 服務器端的統一響應類型
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Data
public class JsonResult {
    /**
     * 響應的業務狀態代碼
     */
    private Integer stateCode;

    /**
     * 操作失敗時的狀態描述文本
     */
    private String message;

    /**
     * 操作成功時的響應數據
     */
    private Object data;

    /**
     * 操作成功時...
     */
    public static JsonResult ok() {
        return ok(null);
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStateCode(ServiceCode.OK.getValue());
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 操作失敗時...
     */
    public static JsonResult fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStateCode(serviceCode.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }

    //出現未知錯誤時
    public static JsonResult unknownFail() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStateCode(ServiceCode.ERROR_UNKNOWN.getValue());
        jsonResult.setMessage("服務器同時訪問人數較多，請稍候再訪問或聯絡技術人員尋求協助");
        return jsonResult;
    }
}
