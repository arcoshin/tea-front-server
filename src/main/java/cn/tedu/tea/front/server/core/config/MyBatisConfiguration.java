package cn.tedu.tea.front.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
@MapperScan("cn.tedu.tea.front.server.content.dao.persist.mapper")
@MapperScan("cn.tedu.tea.front.server.account.dao.persist.mapper")
public class MyBatisConfiguration {
    public MyBatisConfiguration() {
        log.info("創建配置類對象：MyBatisConfiguration");
    }
}
