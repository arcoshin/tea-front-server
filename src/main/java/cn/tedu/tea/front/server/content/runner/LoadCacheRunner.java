package cn.tedu.tea.front.server.content.runner;

import cn.tedu.tea.front.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
//@Component
public class LoadCacheRunner implements ApplicationRunner {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("開始執行【加載緩存數據】");
        categoryService.rebuildListCathe();
    }
}
