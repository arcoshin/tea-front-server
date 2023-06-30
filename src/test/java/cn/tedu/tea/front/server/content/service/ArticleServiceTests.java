package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ArticleServiceTests {

    @Autowired
    private IArticleService service;

    @Test
    void listByCategoryId() {
        Long categoryId = 1L;
        Integer pageNum = 1;
        Integer pageSize = 5;

        try {
            PageData<?> pageData = service.listByCategoryId(categoryId, pageNum, pageSize);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }

        try {
            PageData<?> pageData = service.listByCategoryId(categoryId, pageNum);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}