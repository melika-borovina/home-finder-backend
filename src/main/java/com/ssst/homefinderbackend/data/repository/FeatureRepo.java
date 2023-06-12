package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepo extends JpaRepository<FeatureEntity, Integer> {
    FeatureEntity findOneByName(String name);

}
