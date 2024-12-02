package com.kcalculator.account.bo;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kcalculator.account.dto.UserProfileDTO;
import com.kcalculator.account.dto.UserSimpleDTO;
import com.kcalculator.account.entity.UserEntity;
import com.kcalculator.account.repository.UserRepository;
import com.kcalculator.common.Encrypter;
import com.kcalculator.common.FileManagerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserBO {
	
	private final Encrypter encrypter;
	private final UserRepository userRepository;
	private final FileManagerService fileManager;
	
	// TODO 기능 구현 - 비밀번호 수정(BO)
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
	
	/** TODO getUserProfileByLoginID()와 합치기
	 * 아이디로 사용자를 조회하고 결과 없을 경우 null
	 * @param loginId - String
	 * @return UserSimpleDTO 또는 null
	 */
	public UserSimpleDTO getUserSimpleByLoginId(String loginId) {
		UserEntity userEntity = userRepository.findByLoginId(loginId).orElse(null);
		
		if (userEntity != null) {
			return UserSimpleDTO.builder()
					.id(userEntity.getId())
					.profileImagePath(userEntity.getProfileImagePath())
					.loginId(userEntity.getLoginId())
					.nickname(userEntity.getNickname())
					.build();
		}
		return null;
	}
	
	/** TODO UserSimpleDTO와 UserProfileDTO 하나로 합치기
	 * 프로필 수정을 위한 DTO
	 * @param loginId
	 * @return
	 */
	public UserProfileDTO getUserProfileByLoginId(String loginId) {
		UserEntity userEntity = userRepository.findByLoginId(loginId).orElse(null);
		if (userEntity != null) {
			return UserProfileDTO.builder()
					.id(userEntity.getId())
					.profileImagePath(userEntity.getProfileImagePath())
					.loginId(userEntity.getLoginId())
					.nickname(userEntity.getNickname())
					.email(userEntity.getEmail())
					.build();
		}
		return null;
	}
	
	/**
	 * 회원가입
	 * @param loginId - 로그인에 사용하는 아이디
	 * @param password - 비밀번호
	 * @param nickname - 닉네임
	 * @param email - 이메일 
	 * @return 가입 성공 시 {@code true}<br> 실패 시 {@code false}
	 */
	public boolean addUser(String loginId, String password,
			String nickname, String email) {
		// 이미 존재하는 사용자인지 확인
		if (userRepository.findByLoginId(loginId).orElse(null) != null) return false;
		
		// 암호화(salt를 곁들인)
		Map<String, String> passwordMap = encrypter.generateHash(password);
		String hashedPassword = passwordMap.get("encryptedString");
		String salt = passwordMap.get("salt");
		
		UserEntity userEnttity = UserEntity.builder()
				.loginId(loginId)
				.password(hashedPassword)
				.passwordSalt(salt)
				.nickname(nickname)
				.email(email)
				.build();
		
		try {
			// 성공 시 true, 실패 시 false
			return userRepository.save(userEnttity) != null;
		} catch(Exception e) {
			log.info("[### UserBO] 회원가입 실패");
			return false;
		}
		
	}
	
	/**
	 * 프로필 수정
	 * @param loginId
	 * @param file
	 * @param resetProfileImage
	 * @param nickname
	 * @param email
	 * @return
	 */
	@Transactional
	public boolean editProfile(String loginId, MultipartFile file, boolean resetProfileImage, String nickname, String email) {
		UserEntity user = userRepository.findByLoginId(loginId).orElseThrow();
		String oldFilePath = user.getProfileImagePath();
		String filePath;
		

		if (file != null) { // 프사 업데이트
			filePath = fileManager.uploadFile(file, loginId);
			// NOTE 만약 업데이트 실패하면 프사만 날아가는데, 개선하려면 서순을 바꿔야할 수도 있을 듯
			if (oldFilePath != null) {
				fileManager.deleteFile(oldFilePath);
			}
		} else { // 프사 유지
			filePath = user.getProfileImagePath();
		}
		
		if (resetProfileImage && oldFilePath != null) { // 프사 null로 초기화
			fileManager.deleteFile(oldFilePath);
			filePath = null;
		}

		user.updateProfile(filePath, nickname, email);
		
		
		return true;
	}
	
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @return 아이디와 비번 일치하면 해당하는 UserSimPleDTO, 아니면 null  
	 */
	public UserSimpleDTO logIn(String loginId, String password) {
			
		UserEntity user = userRepository.findByLoginId(loginId).orElse(null);
		if (user == null) {
			return null;
		}
		String salt = user.getPasswordSalt();
		String hashedPassword = user.getPassword();
		
		// 아이디, 비번 일치 시 해당 회원정보 UserSimpleDTO로 반환
		if (encrypter.matchWithEncryptedString(password, hashedPassword, salt) == false) {
			return null;
		}
		return UserSimpleDTO
				.builder()
				.loginId(user.getLoginId())
				.id(user.getId())
				.build();
	}
	
}
