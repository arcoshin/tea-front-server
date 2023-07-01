package cn.tedu.tea.front.server.content.dao.persist.repository.impl;

import cn.tedu.tea.front.server.common.pojo.vo.PageData;
import cn.tedu.tea.front.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.front.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.front.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.front.server.content.pojo.entity.Tag;
import cn.tedu.tea.front.server.content.pojo.vo.TagListItemVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * --------------------------->Here<------------------------------
 * Controller -> Service -> Repository -> Mapper & Xml -> Database
 * -------------------------{           DAO          }------------
 */

/**
 * 處理標籤數據的儲存庫接口
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class TagRepositoryImpl implements ITagRepository {

    @Autowired
    private TagMapper tagMapper;

    public TagRepositoryImpl() {
        log.info("創建儲存庫對象：TagRepositoryImpl");
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢標籤列表】，頁碼:{}，每頁紀錄數:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TagListItemVO> tagList = tagMapper.list();
        PageInfo<TagListItemVO> pageInfo = new PageInfo<>(tagList);
        PageData<TagListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
}
