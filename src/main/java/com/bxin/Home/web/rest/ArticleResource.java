package com.bxin.Home.web.rest;

import com.bxin.Home.domain.Article;
import com.bxin.Home.service.ArticleService;
import com.bxin.Home.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小王子
 */
@RestController
@RequestMapping("/api/article")
public class ArticleResource {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> all() {

        return ResponseEntity.ok(articleService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.addNew(article));
    }

}
