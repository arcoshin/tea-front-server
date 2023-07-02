package cn.tedu.tea.front.server.content.dao.cache;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.front.server.content.pojo.vo.ArticleListItemVO;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleCacheRepositoryTests {

    @Autowired
    private IArticleRepository dbRepository;

    @Autowired
    private IArticleCacheRepository cacheRepository;

    @Test
    void save() {

        PageData<ArticleListItemVO> pageData = dbRepository.list(1, 10);
        List<ArticleListItemVO> list = pageData.getList();
        cacheRepository.save(list);
    }

    @Test
    void delete() {
        boolean deleteResult = cacheRepository.deleteList();
        System.out.println("刪除緩存中的數據，結果：" + deleteResult);
    }

    @Test
    void list() {
        List<?> list = cacheRepository.list();
        System.out.println("從緩存中讀取列表，列表項的數量：" + list.size());
        for (Object item : list) {
            System.out.println(item);
        }
    }
}
