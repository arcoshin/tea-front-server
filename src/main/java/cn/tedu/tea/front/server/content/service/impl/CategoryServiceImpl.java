package cn.tedu.tea.front.server.content.service.impl;

import cn.tedu.tea.front.server.content.dao.cache.ICategoryCacheRepository;
import cn.tedu.tea.front.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.front.server.content.pojo.vo.CategoryListItemVO;
import cn.tedu.tea.front.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * -------------->Here<-------------------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理類別數據的業務實現類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICategoryCacheRepository categoryCacheRepository;

    public CategoryServiceImpl() {
        log.debug("創建業務類對象：CategoryServiceImpl");
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始處理【查詢類別數據列表】的業務，參數：無");

        List<CategoryListItemVO> categoryListItemVOList = categoryCacheRepository.list();
        return categoryListItemVOList;
    }

    @Override
    public void rebuildListCathe() {
        log.debug("開始處理【重建緩存中的類別列表】的業務，參數：無");

        categoryCacheRepository.deleteList();
        List<CategoryListItemVO> list = categoryRepository.list();
        categoryCacheRepository.save(list);
    }


}
