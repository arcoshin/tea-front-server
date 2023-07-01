package cn.tedu.tea.front.server.content.dao.cache;

import cn.tedu.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryCacheRepositoryTests {

    @Autowired
    private ICategoryRepository dbRepository;

    @Autowired
    private ICategoryCacheRepository cacheRepository;

    @Test
    void save(){
        List<CategoryListItemVO> list = dbRepository.list();
        cacheRepository.save(list);
    }

    @Test
    void delete(){
        boolean deleteResult = cacheRepository.deleteList();
        System.out.println("刪除緩存中的數據，結果：" + deleteResult);
    }

    @Test
    void list(){
        List<?> list = cacheRepository.list();
        System.out.println("從緩存中讀取列表，列表項的數量：" + list.size());
        for (Object item : list) {
            System.out.println(item);
        }
    }
}
