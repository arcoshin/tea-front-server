package cn.tedu.tea.front.server.content.service.impl;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import cn.tedu.tea.front.server.content.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * -------------->Here<-------------------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理標籤數據的業務實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class TagServiceImpl implements ITagService {

    @Value("${tea-store.dao.default-query-page-size}")//從配置文件獲取自定義數據
    private Integer defaultQueryPageSize;

    @Autowired
    private ITagRepository tagRepository;

    public TagServiceImpl() {
        log.debug("創建業務類對象：TagServiceImpl");
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢標籤列表】業務，頁碼：{}", pageNum);
        PageData<TagListItemVO> pageData = tagRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢標籤列表】業務，頁碼：{}，每頁紀錄數:{}", pageNum, pageSize);
        PageData<TagListItemVO> pageData = tagRepository.list(pageNum, pageSize);
        return pageData;
    }

}
