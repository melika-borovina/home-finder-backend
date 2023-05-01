package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.TourBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TourBookingRepo extends JpaRepository<TourBookingEntity, Integer> {

    List<TourBookingEntity> findAllByRealEstateId(Integer realEstateId);
}
