package com.example.server.service;

import com.example.server.common.PageResult;
import com.example.server.entity.Article;

public interface ArticleService {
    PageResult<Article> findPage(String keyword, String category, Integer status, int page, int size);
    Article findById(Long id);
    Article findByIdWithView(Long id);
    void save(Article article);
    void update(Article article);
    void delete(Long id);
}
