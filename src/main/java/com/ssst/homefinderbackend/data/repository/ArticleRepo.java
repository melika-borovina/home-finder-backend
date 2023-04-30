package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<ArticleEntity, Integer> {
    ArticleEntity findOneByTitle(String title);
}
