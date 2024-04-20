package com.study.springsecurityjwt.repository;

import com.study.springsecurityjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username); //유저가 이미 있는지 확인
}
