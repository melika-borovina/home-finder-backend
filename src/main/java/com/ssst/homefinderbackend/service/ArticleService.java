package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.ArticleDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public ArticleDto createArticle(ArticleDto article) {
        article.setId(1);
        article.setTitle("Inflation");
        article.setContent("Inflation is 5%");
        article.setAuthor_name("Nejra");
        article.setCreated_at(LocalTime.now());
        return article;
    }

    public List<ArticleDto> getArticles() {
        List<ArticleDto> result = new ArrayList<>();
        ArticleDto x = new ArticleDto(2, "Sarajevo", "Sarajevo BIH", LocalTime.now(),"nejra");
        ArticleDto y = new ArticleDto(3, "Zagreb",  "Zagreb CRO",LocalTime.now(),"nejra");
        result.add(x);
        result.add(y);
        return result;
    }


    public ArticleDto getArticle(Integer id) {
        return new ArticleDto(id,"Zagreb",  "Zagreb CRO",LocalTime.now(),"nejra");
    }

    public ArticleDto updateArticle(Integer id, ArticleDto article) {
        article.setId(id);
        article.setTitle("Mostar");
        return article;
    }

    public void deleteArticle(Integer id) {
        System.out.println("Deleted " + id);
    }
}
