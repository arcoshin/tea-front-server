package cn.tedu.tea.front.server.common.ex;

import cn.tedu.tea.front.server.common.web.ServiceCode;
import lombok.Getter;

/**
 * 業務異常類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public class ServiceException extends RuntimeException {
    @Getter
    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

}
