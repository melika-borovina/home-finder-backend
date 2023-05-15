package com.ssst.homefinderbackend.services;

import com.ssst.homefinderbackend.data.ArticlesTest;
import com.ssst.homefinderbackend.model.ArticleDto;
import com.ssst.homefinderbackend.data.entity.ArticleEntity;
import com.ssst.homefinderbackend.data.repository.ArticleRepo;
import com.ssst.homefinderbackend.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ArticleServiceUnitTest {
    @MockBean
    private ArticleRepo articleRepository;

    @TestConfiguration
    static class ArticlesServiceTestConfiguration {

        @Bean
        @Primary
        public ArticleService articleService(ArticleRepo articleRepository) {
            return new ArticleService(articleRepository);
        }
    }

    @Autowired
    private ArticleService articleService;

    @Test
    public void givenArticles_whenGetArticleList_thenListShouldBeFound() {
        // arrange
        Mockito.when(articleRepository.findAll())
                .thenReturn(List.of(ArticlesTest.article()));

        // act
        List<ArticleEntity> returnedList = articleService.getArticleList();

        // assert
        assertThat(returnedList).hasSize(1);
    }
    @Test
    public void givenNoArticles_whenGetArticleList_thenListShouldBeEmpty() {
        // act
        List<ArticleEntity> returnedList = articleService.getArticleList();

        // assert
        assertThat(returnedList).isEmpty();
    }

    @Test
    public void givenValidId_whenGetArticle_thenArticleShouldBeFound() throws Exception {
        // arrange
        Integer id = 123;
        Mockito.when(articleRepository.findById(id))
                .thenReturn(Optional.of(ArticlesTest.article()));

        // act
        ArticleEntity resultArticle = articleService.getArticle(id);

        // assert
        assertThat(resultArticle.getTitle()).isEqualTo("This is a test article");
    }

    @Test
    public void givenInvalidId_whenGetArticle_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> articleService.getArticle(404))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find");
    }

    @Test
    public void givenArticle_whenCreateArticle_thenArticleIsReturned() throws Exception {
        // arrange
        ArticleDto inputArticleDto = ArticlesTest.articleDto1();
        ArticleEntity outputArticle = ArticlesTest.article();

        Mockito.when(articleRepository.save(any(ArticleEntity.class)))
                .thenReturn(outputArticle);

        // act
        ArticleEntity resultArticle = articleService.createArticle(inputArticleDto);

        // assert
        assertThat(resultArticle).isNotNull();
        assertThat(resultArticle.getTitle()).isEqualTo(inputArticleDto.getTitle());
        assertThat(resultArticle.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenArticle_whenCreateArticle_thenRepositoryCalled() throws Exception {
        // arrange
        ArticleDto articleDto = ArticlesTest.articleDto3();

        Mockito.when(articleRepository.save(any(ArticleEntity.class)))
                .thenReturn(ArticlesTest.article());

        // act
        articleService.createArticle(articleDto);

        // assert
        verify(articleRepository, times(1)).save(any(ArticleEntity.class));
    }

    @Test
    public void givenArticleAndValidId_whenUpdate_thenArticleReturned() throws Exception {
        // arrange
        ArticleDto inputArticleDto = ArticlesTest.articleDto1();
        inputArticleDto.setId(0); // reset id
        Integer id = 123;
        ArticleEntity outputArticle = ArticlesTest.article();
        outputArticle.setId(id);

        Mockito.when(articleRepository.findById(id))
                .thenReturn(Optional.of(outputArticle));
        Mockito.when(articleRepository.save(any(ArticleEntity.class)))
                .thenReturn(outputArticle);

        // act
        ArticleEntity resultArticle = articleService.updateArticle(id, inputArticleDto);

        // assert
        assertThat(resultArticle).isNotNull();
        assertThat(resultArticle.getTitle()).isEqualTo(inputArticleDto.getTitle());
        assertThat(resultArticle.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> articleService.updateArticle(404, ArticlesTest.articleDto3()))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find feature with id");
    }

    @Test
    public void givenMilestone_whenDelete_thenRepositoryCalled() {
        // arrange
        Integer id = 123;

        // act
        articleService.deleteArticle(id);

        // assert
        verify(articleRepository, times(1)).deleteById(id);
    }



}
