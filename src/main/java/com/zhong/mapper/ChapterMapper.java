package com.zhong.mapper;

import com.zhong.pojo.po.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_chapter(章节表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Chapter
 */
@Repository
public interface ChapterMapper extends BaseMapper<Chapter> {

}




