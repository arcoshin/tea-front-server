package cn.tedu.tea.front.server.content.dao.persist.repository;

import cn.tedu.tea.front.server.content.pojo.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CommentRepositoryTests {

    @Autowired
    private ICommentRepository repository;

    @Test
    void insert() {
        Comment comment = new Comment();
        comment.setAuthorId(1L);
        comment.setArticleId(1L);
        comment.setContent("新增一條評論測試！");

        System.out.println("插入數據之前，參數：" + comment);
        int rows = repository.insert(comment);
        System.out.println("插入數據完成，受影響的行數：" + rows);
        System.out.println("插入數據之後，參數：" + comment);
    }

    @Test
    void listByArticleId() {
        Long articleId = 1L;
        Integer pageNum = 1;
        Integer pageSize = 5;
        List<?> list = repository.listByArticleId(articleId, pageNum, pageSize).getList();
        System.out.println("查詢列表數據完成，列表長度：" + list.size());
        for (Object item : list) {
            System.out.println("列表項：" + item);
        }
    }

}