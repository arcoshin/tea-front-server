package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
//@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
//@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CategoryServiceTests {

    @Autowired
    private ICategoryService service;

    @Test
    void list() {
        List<?> list = service.list();
        System.out.println("查詢列表數據完成，列表長度：" + list.size());
        for (Object item : list) {
            System.out.println("列表項：" + item);
        }
    }

    @Test
    void rebuild(){
        service.rebuildListCathe();
    }

}