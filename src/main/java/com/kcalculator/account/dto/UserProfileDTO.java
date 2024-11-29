package com.kcalculator.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileDTO {
	private int id;
	private String profileImagePath;
	private String loginId;
	private String nickname;
	private String email;
}