package com.kcalculator.account.BO;

import org.springframework.stereotype.Service;

import com.kcalculator.account.entity.UserEntity;
import com.kcalculator.account.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserBO {
	
	private final UserRepository UserRepository;
	
	/*
		UserEntity userEntity = UserRepository.findByLoginId(loginId);
		
		UserDTO userDTO = UserDTO.builder()
				.id(userEntity.getId())
				.profileImagePath(userEntity.getProfileImagePath())
				.loginId(userEntity.getLoginId())
				.password(userEntity.getPassword())
				.nickname(userEntity.getNickname())
				.email(userEntity.getEmail())
				.createdAt(userEntity.getCreatedAt())
				.updatedAt(userEntity.getUpdatedAt())
				.build();
	 */
	
	
	// 아이디 중복확인
	public boolean isDuplicateId(String loginId) {
		UserEntity userEntity = UserRepository.findByLoginId(loginId);
		
		return userEntity != null;
	}
	
	
	// 회원가입
	public boolean addUser(String loginId, String password, 
			String nickname, String email) {
		UserEntity userEnttity = UserEntity.builder()
				.profileImagePath(email)
				.loginId(loginId)
				.password(password)
				.nickname(nickname)
				.email(email)
				.build();
		
		try {
			UserRepository.save(userEnttity);
		} catch(Exception e) {
			throw new RuntimeException();
//			log.info("[### UserBO] 회원가입 실패");
//			return false;
		}
		
		return true;
	}
	
}
