package cn.tedu.tea.front.server.content.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 處理類別緩存的計畫任務類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Component
public class CategoryCacheSchedule {

    public CategoryCacheSchedule() {
        log.debug("創建計畫任務組件對象：CategoryCacheSchedule");
    }

    //fixedRate：執行頻率，單位毫秒，以前次執行的'開始'時間來計算下一次的執行時間(較推薦，但要注意執行過程時間不可過長)
    //fixedDelay：執行間隔，單位毫秒，以前次執行的'結束'時間來計算下一次的執行時間(週期會逐漸失準，但較適合耗時任務)
    //cron：使用cron表達式來配置執行時間

    @Scheduled(fixedRate = 2 * 1000)
    public void rebuildListCache() {
        log.debug("開始執行【重建緩存中的類別列表】的計畫任務");
    }

    /**
     * cron表達式
     * cron表達式本質就是一個字符串，這個字符串由『6或7個域組成』
     * cron表達式中的域，從左至右一次為：秒 分 時 日 月 周 (年)，其中年可省略
     *
     * --表達式中各域的值可以使用通配符--
     * ---->使用星號 * 表示任意值
     * ---->使用減號 - 表示區間
     * ---->使用逗號 , 表示列舉，但切記不可空格！
     * ---->使用問號 ? 表示不關心此值，但此通配符只能用於『日』和『周』兩個域
     * ---->域數字值，可以改寫為"x/y"的格式，表示x時執行，間隔y再執行
     * 例如
     * @Scheduled(cron = "0 0 9 ? 5-7 ?")
     * 這代表「(每年)五到七月的每天早上九點執行一次(不在乎星期幾也不在乎日期--->每天)」
     */
}
