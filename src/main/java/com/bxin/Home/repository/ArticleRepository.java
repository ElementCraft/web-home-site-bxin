package com.bxin.Home.repository;

import com.bxin.Home.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByDeletedOrderByGmtCreateDesc(Boolean deleted);

    List<Article> findAllByNavIdAndDeletedOrderByGmtCreateDesc(Long id, Boolean deleted);
}
