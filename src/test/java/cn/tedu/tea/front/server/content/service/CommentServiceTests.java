package cn.tedu.tea.front.server.content.service;

import cn.tedu.tea.front.server.common.ex.ServiceException;
import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import cn.tedu.tea.front.server.content.pojo.param.CommentAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CommentServiceTests {

    @Autowired
    private ICommentService service;

    @Test
    void addNew() {
        CurrentPrincipal currentPrincipal = new CurrentPrincipal();
        currentPrincipal.setId(1L);
        currentPrincipal.setUsername("root");

        String remoteAddr = "127.0.0.1";

        CommentAddNewParam commentAddNewParam = new CommentAddNewParam();
        commentAddNewParam.setArticleId(1L);
        commentAddNewParam.setContent("新增一條評論測試！");

        try {
            service.addNew(currentPrincipal, remoteAddr, commentAddNewParam);
            System.out.println("添加數據完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void listByArticleId() {
        Long articleId = 1L;
        Integer pageNum = 1;
        Integer pageSize = 5;

        try {
            PageData<?> pageData = service.listByArticleId(articleId, pageNum, pageSize);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }

        try {
            PageData<?> pageData = service.listByArticleId(articleId, pageNum);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}