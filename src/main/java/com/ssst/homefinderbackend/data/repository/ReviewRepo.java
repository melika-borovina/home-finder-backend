package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Integer> {
    ReviewEntity findOneByTitle(String title);
}
