package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.ArticleEntity;
import com.ssst.homefinderbackend.model.ArticleDto;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<ArticleEntity>> getArticlesList() {
        return new ResponseEntity<>(this.articleService.getArticleList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getArticle(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.articleService.getArticle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createArticle(@RequestBody ArticleDto article) {
        try {
            return new ResponseEntity<>(this.articleService.createArticle(article), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateArticle(@PathVariable Integer id, @RequestBody ArticleDto article) {
        try {
            return new ResponseEntity<>(this.articleService.updateArticle(id, article), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteArticle(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.articleService.deleteArticle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
