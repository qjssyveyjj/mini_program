package com.example.server.mapper;

import com.example.server.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 健康资讯数据访问接口
 *
 * @author Java1234
 */
@Mapper
public interface ArticleMapper {

    Article findById(@Param("id") Long id);

    List<Article> findPage(@Param("keyword") String keyword, @Param("category") String category,
                           @Param("status") Integer status, @Param("offset") int offset, @Param("limit") int limit);

    long count(@Param("keyword") String keyword, @Param("category") String category, @Param("status") Integer status);

    int insert(Article article);

    int update(Article article);

    int deleteById(@Param("id") Long id);

    int incrementViewCount(@Param("id") Long id);

    long countAll();
}
