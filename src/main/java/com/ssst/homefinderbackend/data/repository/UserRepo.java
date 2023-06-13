package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findOneByUsername(String username);
    boolean existsByUsername(String username);
}