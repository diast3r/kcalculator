package com.kcalculator.account.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSimpleDTO {
	private int id;
	private String profileImagePath;
	private String loginId;
	private String nickname;
}
