package cn.tedu.tea.front.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;

/**
 * Validation配置類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
public class ValidationConfiguration {

    public ValidationConfiguration() {
        log.debug("創建配置類對象：ValidationConfiguration");
    }

    @Bean
    public javax.validation.Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure() // 開始配置
                .failFast(true) // 配置快速失敗
                .buildValidatorFactory() // 構建Validator工廠
                .getValidator(); // 從Validator工廠中獲取Validator對象
    }

}