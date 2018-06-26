package com.bxin.Home.service;

import com.bxin.Home.domain.Article;
import com.bxin.Home.repository.ArticleRepository;
import com.bxin.Home.tools.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 获取全部留言信息
     *
     * @return
     */
    public List<Article> getAll() {
        return articleRepository.findAllByDeletedOrderByGmtCreateDesc(Boolean.FALSE);
    }

    /**
     * 新增文章
     *
     * @param article 文章
     * @return
     */
    public Result addNew(Article article) {

        article.setId(null);
        articleRepository.save(article);

        return Result.ok();
    }

    public List<Article> getAllByNavId(Long id) {
        return articleRepository.findAllByNavIdAndDeletedOrderByGmtCreateDesc(id, Boolean.FALSE);
    }

    public Article getOneById(Long id) {
        return articleRepository.findOne(id);
    }

    public Article fix(Article article) {
        Article dbArticle = articleRepository.findOne(article.getId());

        if (StringUtils.hasText(article.getTitle())) {
            dbArticle.setTitle(article.getTitle());
        }

        if (StringUtils.hasText(article.getContent())) {
            dbArticle.setContent(article.getContent());
        }

        return articleRepository.save(dbArticle);
    }

    public Result del(Long id) {
        Article dbArticle = articleRepository.findOne(id);
        dbArticle.setDeleted(Boolean.TRUE);
        articleRepository.save(dbArticle);

        return Result.ok();
    }
}
