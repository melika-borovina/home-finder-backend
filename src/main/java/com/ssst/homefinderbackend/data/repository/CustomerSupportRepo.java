package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.CustomerSupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSupportRepo extends JpaRepository<CustomerSupportEntity, Integer> {
}
