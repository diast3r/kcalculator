package com.kcalculator.account.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
	private int id;
	private String profile_image_path;
	private String login_id;
	private String password;
	private String nickname;
	private String email;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
