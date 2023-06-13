package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.ContactUsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepo extends JpaRepository<ContactUsEntity, Integer> {
}
