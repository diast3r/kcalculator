package com.kcalculator.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcalculator.account.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByLoginId(String loginId);
	
	
}
