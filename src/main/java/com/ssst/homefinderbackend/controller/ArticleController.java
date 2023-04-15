package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.model.ArticleDto;
import com.ssst.homefinderbackend.service.ArticleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/article")
@RestController
public class ArticleController {
    private final ArticleService articleService;

    ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/list")
    public List<ArticleDto> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public ArticleDto createArticle(@RequestBody ArticleDto article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/{id}")
    public ArticleDto updateArticle(@PathVariable Integer id, @RequestBody ArticleDto article) {
        return articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
    }
}
