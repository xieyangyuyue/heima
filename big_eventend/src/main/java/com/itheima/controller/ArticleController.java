
package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 新增分类
     *
     * @param article
     * @return
     * @RequestBody json转换成实体类对象
     */
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    /**
     * 文章列表分页列表查询
     *
     * @param pageNum    当前页码
     * @param pageSize   每页条数
     * @param categoryId 文章分类ID
     * @param state      发布状态
     * @return
     * @RequestParam(required = false)    提示浏览器参数可以不传（避免报错）
     */

    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer state
    ) {
       PageBean<Article>pb= articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}
