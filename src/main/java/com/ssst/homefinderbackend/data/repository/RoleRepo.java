package com.ssst.homefinderbackend.data.repository;

import com.ssst.homefinderbackend.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findOneByName(String name);
    boolean existsByName(String name);

}