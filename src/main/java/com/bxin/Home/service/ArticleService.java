package com.bxin.Home.service;

import com.bxin.Home.domain.Article;
import com.bxin.Home.repository.ArticleRepository;
import com.bxin.Home.tools.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return articleRepository.findAll(new Sort(Sort.Direction.DESC, "gmtCreate"));
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
}
