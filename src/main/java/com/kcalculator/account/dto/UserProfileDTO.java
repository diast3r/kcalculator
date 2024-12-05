package com.kcalculator.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDTO {
	// TODO 유효성 검사 - UserProfileDTO
	private int id;
	private String profileImagePath;
	private String loginId;
	private String nickname;
	private String email;
}