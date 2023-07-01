package cn.tedu.tea.front.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 週期任務配置類
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    public ScheduleConfiguration() {
        log.debug("創建配置類對象：ScheduleConfiguration");
    }

}
