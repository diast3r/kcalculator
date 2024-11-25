package com.kcalculator.account.BO;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import com.kcalculator.account.dto.UserDTO;
import com.kcalculator.account.entity.UserEntity;
import com.kcalculator.account.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountBO {
	
	private final UserRepository UserRepository;
	
	/*
		UserEntity userEntity = UserRepository.findByLoginId(loginId);
		
		UserDTO userDTO = UserDTO.builder()
				.id(userEntity.getId())
				.profile_image_path(userEntity.getProfile_image_path())
				.login_id(userEntity.getLogin_id())
				.password(userEntity.getPassword())
				.nickname(userEntity.getNickname())
				.email(userEntity.getEmail())
				.created_at(userEntity.getCreated_at())
				.updated_at(userEntity.getUpdated_at())
				.build();
	 */
	
	
	// 아이디 중복확인
	public boolean isDuplicateId(String loginId) {
		UserEntity userEntity = UserRepository.findByLoginId(loginId);
		
		return userEntity != null;
	}
	
	
	
	
	
}
