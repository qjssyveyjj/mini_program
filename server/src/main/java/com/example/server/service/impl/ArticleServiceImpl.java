package com.example.server.service.impl;

import com.example.server.common.PageResult;
import com.example.server.entity.Article;
import com.example.server.mapper.ArticleMapper;
import com.example.server.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 健康资讯业务实现类
 *
 * @author Java1234
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public PageResult<Article> findPage(String keyword, String category, Integer status, int page, int size) {
        int offset = (page - 1) * size;
        List<Article> records = articleMapper.findPage(keyword, category, status, offset, size);
        long total = articleMapper.count(keyword, category, status);
        return new PageResult<>(total, records);
    }

    @Override
    public Article findById(Long id) { return articleMapper.findById(id); }

    @Override
    public Article findByIdWithView(Long id) {
        articleMapper.incrementViewCount(id);
        return articleMapper.findById(id);
    }

    @Override
    public void save(Article article) {
        if (article.getViewCount() == null) article.setViewCount(0);
        if (article.getStatus() == null) article.setStatus(1);
        articleMapper.insert(article);
    }

    @Override
    public void update(Article article) { articleMapper.update(article); }

    @Override
    public void delete(Long id) { articleMapper.deleteById(id); }
}
