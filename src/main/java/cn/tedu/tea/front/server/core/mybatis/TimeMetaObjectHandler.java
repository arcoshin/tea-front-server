package cn.tedu.tea.front.server.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 基於MyBatis Plus的自動填充時間的處理器類
 * (自動處理表格字段為gmt_create 數據創建時間 & gmt_modified 最後更新時間的兩個數據)
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {

    /**
     * 數據創建時間的屬性名
     */
    public static final String FIELD_CREATE_TIME = "gmtCreate";

    /**
     * 最後修改時間的屬性名
     */
    public static final String FIELD_UPDATE_TIME = "gmtModified";

    public TimeMetaObjectHandler() {
        log.info("創建MyBatis Plus的自動填充數據的處理器對象：TimeMetaObjectHandler");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME, now, metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }

}