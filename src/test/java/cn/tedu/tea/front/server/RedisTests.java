package cn.tedu.tea.front.server;

import cn.tedu.tea.front.server.content.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    
    @Test
    void setValue() {//ops:操作
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("username123","Tom");
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
        opsForValue.set("article1",article);
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
    void delete(){
        String key = "username1";
        Boolean deleteResult = redisTemplate.delete(key);
        System.out.println(deleteResult);
    }

    @Test
    void deleteBatch(){
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
    void keys(){
        String pattern = "*";
        Set<String> keys = redisTemplate.keys(pattern);
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
