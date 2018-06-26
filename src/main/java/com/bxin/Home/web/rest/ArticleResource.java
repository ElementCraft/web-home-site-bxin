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

    @GetMapping("/nav")
    public ResponseEntity<List<Article>> nav(Long id) {
        return ResponseEntity.ok(articleService.getAllByNavId(id));
    }

    @GetMapping("/info")
    public ResponseEntity<Article> info(Long id) {
        return ResponseEntity.ok(articleService.getOneById(id));
    }

    @PutMapping("/fix")
    public ResponseEntity<Article> fix(@RequestBody Article article) {
        return ResponseEntity.ok(articleService.fix(article));
    }

    @DeleteMapping("/del")
    public ResponseEntity<Result> del(Long id) {
        return ResponseEntity.ok(articleService.del(id));
    }
}
