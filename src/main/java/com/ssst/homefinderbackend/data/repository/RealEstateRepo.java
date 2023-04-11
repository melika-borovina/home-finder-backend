package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepo extends JpaRepository<RealEstateEntity, Integer> {
    RealEstateEntity findOneByImgUrl(String imgUrl);

}