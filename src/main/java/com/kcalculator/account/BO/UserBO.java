package com.kcalculator.account.BO;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kcalculator.account.dto.UserSimpleDTO;
import com.kcalculator.account.entity.UserEntity;
import com.kcalculator.account.repository.UserRepository;
import com.kcalculator.common.Encrypter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserBO {
	
	private final Encrypter encrypter;
	private final UserRepository userRepository;
	
	/*
	public UserBO (Encrypter encrypter, UserRepository userRpeository) {
		this.encrypter = encrypter;
		this.userRepository = userRpeository;
	}
	*/
	
	/*
		UserEntity userEntity = userRepository.findByLoginId(loginId);
		
		UserDTO userDTO = UserDTO.builder()
				.id(userEntity.getId())
				.profileImagePath(userEntity.getProfileImagePath())
				.loginId(userEntity.getLoginId())
				.password(userEntity.getPassword())
				.passwordSalt(userEntity.getPasswordSalt())
				.nickname(userEntity.getNickname())
				.email(userEntity.getEmail())
				.createdAt(userEntity.getCreatedAt())
				.updatedAt(userEntity.getUpdatedAt())
				.build();
	 */
	
	
	// 아이디 중복확인
	public boolean isDuplicateId(String loginId) {
		UserEntity userEntity = userRepository.findByLoginId(loginId).orElse(null);
		
		return userEntity != null;
	}
	
	
	// 회원가입
	public boolean signUp(String loginId, String password,
			String nickname, String email) {
		// 암호화
		Map<String, String> passwordMap = encrypter.generateHash(password);
		String hashedPassword = passwordMap.get("encryptedString");
		String salt = passwordMap.get("salt");
		
		UserEntity userEnttity = UserEntity.builder()
				.profileImagePath(email)
				.loginId(loginId)
				.password(hashedPassword)
				.passwordSalt(salt)
				.nickname(nickname)
				.email(email)
				.build();
		
		try {
			return userRepository.save(userEnttity) != null;
		} catch(Exception e) {
			log.info("[### UserBO] 회원가입 실패");
			return false;
		}
		
	}
	
	
	// 로그인
	public UserSimpleDTO logIn(String loginId, String password) {
			
		UserEntity user = userRepository.findByLoginId(loginId).orElse(null);
		if (user == null) {
			return null;
		}
		String salt = user.getPasswordSalt();
		String hashedPassword = user.getPassword();
		
		// 아이디, 비번 일치 시 해당 회원정보 UserSimpleDTO로 반환
		if (encrypter.matchWithEncryptedString(password, hashedPassword, salt)) {
			return UserSimpleDTO.builder().loginId(user.getLoginId()).id(user.getId()).build();
		}
		return null;
			
	}
	
}
