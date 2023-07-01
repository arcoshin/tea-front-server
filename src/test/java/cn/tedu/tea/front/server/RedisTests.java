package cn.tedu.tea.front.server;

import cn.tedu.tea.front.server.content.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Redis基點類型：string、Hash、list、set、z-set
 */
@SpringBootTest
public class RedisTests {
    /**
     * 當讀寫操作與值無關時，直接調用redisTemplate的方法(如delete)
     * 當需要讀寫Redis中的string類型的數據時，需要先調用opsForValue方法
     * 當需要讀寫Redis中的list類型的數據時，需要先調用opsForList方法
     * 當需要讀寫Redis中的set類型的數據時，需要先調用opsForSet方法
     * ...依此類推
     */

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    void setValue() {//ops:操作
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username123", "Tom");
    }

    @Test
    void getValue() {//ops:操作
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable serializable = opsForValue.get("username123");
        System.out.println(serializable);
    }

    @Test
    void setObject() {//ops:操作
        Article article = new Article();
        article.setAuthorId(1987L);
        article.setAuthorName("JBQ");

        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("article1", article);
    }

    @Test
    void getObject() {//ops:操作
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable serializable = opsForValue.get("article1");
        System.out.println(serializable);
        //強轉還原
        System.out.println(serializable.getClass().getName());
        Article article = (Article) serializable;
        System.out.println(article);
    }

    @Test
    void delete() {
        String key = "username1";
        Boolean deleteResult = redisTemplate.delete(key);
        System.out.println(deleteResult);
    }

    @Test
    void deleteBatch() {
        Set<String> keys = new HashSet<>();
        keys.add("username1");
        keys.add("username2");
        keys.add("username3");
        keys.add("username4");
        keys.add("username5");

        //批量刪除
        Long deleteCount = redisTemplate.delete(keys);
        System.out.println(deleteCount);
    }

    /**
     * 生產禁用
     */
    @Test
    void keys() {
        String pattern = "*";
        Set<String> keys = redisTemplate.keys(pattern);
        for (String key : keys) {
            System.out.println(key);
        }
    }

    //向Redis存入list數據
    @Test
    void save() {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "name-list";
        opsForList.rightPush(key, "name-1");
        opsForList.rightPush(key, "name-2");
        opsForList.rightPush(key, "name-3");
        opsForList.rightPush(key, "name-4");
        opsForList.rightPush(key, "name-5");
        opsForList.rightPush(key, "name-6");
        opsForList.rightPush(key, "name-7");
        opsForList.rightPush(key, "name-8");
        opsForList.rightPush(key, "name-9");
    }

    //向Redis刪除list數據
    @Test
    void deleteList() {
        String key = "name-list";

    }

    //從Redis讀取list數據
    @Test
    void range() {
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        String key = "name-list";
        //redis中的list數據具有雙重下標，但始終以起始下標向右讀取
        long startIndex = 0;
        long endIndex = -1;

        List<Serializable> list = opsForList.range(key, startIndex, endIndex);
        System.out.println("讀取列表完成，列表長度" + list.size());
        for (Serializable serializable : list) {
            System.out.println(serializable);
        }
    }
}
