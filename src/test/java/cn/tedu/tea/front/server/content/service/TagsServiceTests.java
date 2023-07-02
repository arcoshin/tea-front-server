package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TagsServiceTests {

    @Autowired
    private ITagService service;

    @Test
    void list1() {
        Integer pageNum = 1;

        try {
            PageData<TagListItemVO> pageData = service.list(pageNum);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void list2() {
        Integer pageNum = 1;
        Integer pageSize = 3;

        try {
            PageData<TagListItemVO> pageData = service.list(pageNum, pageSize);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }


}