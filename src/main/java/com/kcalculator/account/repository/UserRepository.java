package com.kcalculator.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcalculator.account.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByLoginId(String loginId);
}
