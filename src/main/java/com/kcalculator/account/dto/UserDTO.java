package com.kcalculator.account.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
	// TODO 유효성 검사 -  UserDTO
	private int id;
	private String profileImagePath;
	private String loginId;
	private String password;
	private String nickname;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
