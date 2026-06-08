package com.example.server.controller;

import com.example.server.common.R;
import com.example.server.entity.Article;
import com.example.server.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
 * 健康资讯控制器
 *
 * @author Java1234
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/page")
    public R page(@RequestParam(defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "") String category,
                  @RequestParam(required = false) Integer status,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int size) {
        return R.ok(articleService.findPage(keyword, category, status, page, size));
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean view) {
        if (view) {
            return R.ok(articleService.findByIdWithView(id));
        }
        return R.ok(articleService.findById(id));
    }

    @PostMapping
    public R save(@RequestBody Article article) {
        articleService.save(article);
        return R.ok("发布成功");
    }

    @PutMapping
    public R update(@RequestBody Article article) {
        articleService.update(article);
        return R.ok("更新成功");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        articleService.delete(id);
        return R.ok("删除成功");
    }
}
