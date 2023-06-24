package com.zhong.controller.admin;

import com.zhong.pojo.po.Chapter;
import com.zhong.other.result.BaseResult;
import com.zhong.other.result.PageResult;
import com.zhong.pojo.vo.ChapterVO;
import com.zhong.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhong
 * @Description:
 */
@RestController
@RequestMapping("/admin/chapter")
public class AdminChapterController {

    @Autowired
    ChapterService chapterService;

    /**
     * 查询所有
     * @return 对象列表
     */
    @GetMapping(value = "/queryChapters")
    public PageResult queryChapters(Integer repoId){
        List<ChapterVO> chapterVOS =  chapterService.getChapterVOS(repoId);
        return PageResult.success((long) chapterVOS.size(), chapterVOS);
    }

    /**
     * 保存全部
     * @param chapters
     * @return
     */
    @PostMapping(value = "/saveChapters")
    public BaseResult saveChapters(@RequestBody List<Chapter> chapters){
        return BaseResult.boolResult(chapterService.saveChapters(chapters));
    }

}
