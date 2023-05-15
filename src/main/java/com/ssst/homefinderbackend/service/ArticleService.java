package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.ArticleEntity;
import com.ssst.homefinderbackend.data.repository.ArticleRepo;
import com.ssst.homefinderbackend.model.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {


    @Autowired
    ArticleRepo articleRepository;

    public ArticleService(ArticleRepo articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleEntity validatePayloadAndReturnEntity(Integer articleId, ArticleDto article) throws Exception {
        Objects.requireNonNull(article.getTitle(), "Article Title is required");

        if (articleId != null) {
            ArticleEntity articleEntity = getArticle(articleId);
            if (articleEntity == null) {
                throw new Exception(String.format("Could not find article with id '%s'", articleId));
            }

        }

        ArticleEntity articleDb = new ArticleEntity();
        // in case of insert featureId will be created on repository level
        if (articleId != null) {
            articleDb.setId(articleId);
        }

        articleDb.setTitle(article.getTitle());
        articleDb.setContent(article.getContent());

        return articleDb;
    }

    public ArticleEntity createArticle(ArticleDto article) throws Exception {

        ArticleEntity articleDb = this.validatePayloadAndReturnEntity(null, article);

        ArticleEntity createdArticle = articleRepository.save(articleDb);

        return createdArticle;

    }

    public List<ArticleEntity> getArticleList() {
        return articleRepository.findAll();
    }


    public ArticleEntity getArticle(Integer articleId) throws Exception  {
        Optional<ArticleEntity> result = articleRepository.findById(articleId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception(String.format("Could not find article with id %s", articleId));
        }
    }

    public ArticleEntity updateArticle(Integer articleId, ArticleDto article) throws Exception {

        ArticleEntity articleDb = this.validatePayloadAndReturnEntity(articleId, article);

        ArticleEntity createdArticle = articleRepository.save(articleDb);

        return getArticle(createdArticle.getId());
    }
    public Integer deleteArticle(Integer articleId)  {
        articleRepository.deleteById(articleId);
        return 1;
    }
}
