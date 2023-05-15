package com.ssst.homefinderbackend.data;

import com.ssst.homefinderbackend.model.ArticleDto;
import com.ssst.homefinderbackend.data.entity.ArticleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticlesTest {
    private static Integer id1 = 123;
    private static String title1 = "This is a test article";
    private static String content1 = "This is a test content";
    private static String imageURL1 = "https://test.jpeg";
    private static String author_name1 = "Test author";

    private static LocalTime time1 = LocalTime.of(10, 1, 1);

    public static ArticleEntity article() {
        ArticleEntity article = new ArticleEntity();
        article.setId(id1);
        article.setTitle(title1);
        article.setContent(content1);
        article.setCreatedAt(time1);
        article.setImageURL(imageURL1);
        article.setAuthor_name(author_name1);
        return article;
    }

    public static ArticleDto articleDto1() {
        return new ArticleDto(id1, title1, content1, imageURL1, author_name1);
    }

    public static ArticleDto articleDto3() {
        return new ArticleDto(321, "Test title", "test content", "https://test1.jpeg", "test");
    }

}
