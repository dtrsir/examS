package com.zhong.mapper;

import com.zhong.pojo.po.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhong
 * @description 针对表【t_paper(试卷表)】的数据库操作Mapper
 * @Entity com.zhong.pojo.po.Paper
 */
@Repository
public interface PaperMapper extends BaseMapper<Paper> {

}

